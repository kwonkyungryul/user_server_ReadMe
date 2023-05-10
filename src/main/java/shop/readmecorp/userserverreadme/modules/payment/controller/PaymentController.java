package shop.readmecorp.userserverreadme.modules.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.file.dto.FileDTO;
import shop.readmecorp.userserverreadme.modules.file.service.FileService;
import shop.readmecorp.userserverreadme.modules.membership.entity.Membership;
import shop.readmecorp.userserverreadme.modules.membership.service.MembershipService;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.entity.MembershipPayment;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentConst;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentType;
import shop.readmecorp.userserverreadme.modules.payment.request.BookPaymentSaveRequest;
import shop.readmecorp.userserverreadme.modules.payment.request.MembershipPaymentSaveRequest;
import shop.readmecorp.userserverreadme.modules.payment.response.MyPaymentResponse;
import shop.readmecorp.userserverreadme.modules.payment.response.PaymentResponse;
import shop.readmecorp.userserverreadme.modules.payment.service.BookPaymentService;
import shop.readmecorp.userserverreadme.modules.payment.service.MembershipPaymentService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final BookPaymentService bookPaymentService;
    private final MembershipPaymentService membershipPaymentService;
    private final MembershipService membershipService;
    private final BookService bookService;
    private final FileService fileService;

    public PaymentController(BookPaymentService bookPaymentService, MembershipPaymentService membershipPaymentService, MembershipService membershipService, BookService bookService, FileService fileService) {
        this.bookPaymentService = bookPaymentService;
        this.membershipPaymentService = membershipPaymentService;
        this.membershipService = membershipService;
        this.bookService = bookService;
        this.fileService = fileService;
    }

    @GetMapping("/books/my")
    public ResponseEntity<ResponseDTO<List<BookPaymentDTO>>> getMyPaymentBook(
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        List<BookPaymentDTO> bookPayments = bookPaymentService.getMyPaymentList(
                myUserDetails.getUser(),
                bookService,
                fileService
        );
        return ResponseEntity.ok(new ResponseDTO<>(1, "보관함 - 구매내역 조회가 완료되었습니다.", bookPayments));
    }


    @GetMapping("/my")
    public ResponseEntity<ResponseDTO<MyPaymentResponse>> getMyPaymentResponse(
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        List<BookPaymentDTO> bookPayments = bookPaymentService.getMyPaymentList(
                myUserDetails.getUser(),
                bookService,
                fileService
        );
        // TODO 굳이 할필요없을듯 ㅎ -> 이렇게 되면 멤버십 결제 내역을 볼 수 없음
//        if (bookPayments.size() == 0) {
//            return ResponseEntity.ok(new ResponseDTO<>(1, "구매 결제 내역이 없습니다.", new MyPaymentResponse(new ArrayList<>(), new ArrayList<>())));
//        }
        List<MembershipPaymentDTO> membershipPayments = membershipPaymentService.getMyList(myUserDetails.getUser());
        return ResponseEntity.ok(new ResponseDTO<>(1, "결제 목록 조회가 완료되었습니다.", new MyPaymentResponse(bookPayments, membershipPayments)));
    }


    @PostMapping("/books")
    public ResponseEntity<ResponseDTO<PaymentResponse>> saveBook(
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @Valid @RequestBody BookPaymentSaveRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        List<Book> books = bookService.getBooks(request.getBookIds());
        for (Book book : books) {
            if (!request.getBookIds().contains(book.getId())) {
                throw new Exception400(BookConst.notFound);
            }
        }
        List<BookPaymentDTO> myBookPaymentDtoList = bookPaymentService.getMyList(myUserDetails.getUser());
        List<Integer> myBookIds = myBookPaymentDtoList.stream()
                                                      .map(BookPaymentDTO::getId)
                                                      .collect(Collectors.toList());

        for (Book book : books) {
            if (myBookIds.contains(book.getId())) {
                throw new Exception400("이미 구매한 도서가 존재합니다.");
            }
        }
        Integer paymentNo = bookPaymentService.save(myUserDetails.getUser(), books);

        return ResponseEntity.ok(new ResponseDTO<>(1, "결제 데이터 삽입 완료되었습니다.", new PaymentResponse(paymentNo, PaymentType.BOOK.name())));
    }

    @PostMapping("/membership")
    public ResponseEntity<ResponseDTO<Integer>> saveMembership(
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @Valid @RequestBody MembershipPaymentSaveRequest request
    ) {
        Optional<Membership> optionalMembership = membershipService.getMembership(request.getMembershipId());
        if (optionalMembership.isEmpty()) {
            throw new Exception400("존재하지 않는 멤버십입니다.");
        }

        Integer paymentNo = membershipPaymentService.save(myUserDetails.getUser(), request, optionalMembership.get());
        return ResponseEntity.ok(new ResponseDTO<>(1, "결제 데이터 삽입 완료되었습니다.", paymentNo));
    }

    @DeleteMapping("/{membershipId}/membership")
    public ResponseEntity<ResponseDTO<Void>> deleteMemberShip (
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @PathVariable Integer membershipId
    ) {
        Optional<MembershipPayment> membershipPaymentOptional = membershipPaymentService.getMyMemberShip(myUserDetails.getUser(), membershipId);
        if (membershipPaymentOptional.isEmpty()) {
            throw new Exception400("멤버십 상태가 아닙니다.");
        }

        membershipPaymentService.delete(membershipPaymentOptional.get());
        return ResponseEntity.ok(new ResponseDTO<>(1, "멤버십 해지가 완료되었습니다.", null));
    }
}
