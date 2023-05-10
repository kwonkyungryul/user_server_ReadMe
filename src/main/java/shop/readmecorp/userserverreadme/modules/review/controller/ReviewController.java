package shop.readmecorp.userserverreadme.modules.review.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.common.exception.Exception401;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.review.ReviewConst;
import shop.readmecorp.userserverreadme.modules.review.dto.MyReviewDTO;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.request.ReviewSaveRequest;
import shop.readmecorp.userserverreadme.modules.review.service.ReviewService;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final BookService bookService;
    private final UserService userService;

    public ReviewController(ReviewService reviewService, BookService bookService, UserService userService) {
        this.reviewService = reviewService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/my")
    public ResponseEntity<ResponseDTO<List<MyReviewDTO>>> myReview(@AuthenticationPrincipal MyUserDetails myUserDetails) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "나의 리뷰 조회 완료되었습니다.", reviewService.getList(myUserDetails.getUser())));
    }

    @GetMapping("/{bookId}/book")
    public ResponseEntity<ResponseDTO<Page<ReviewNoneBookDTO>>> bookReview(
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @PathVariable Integer bookId,
            Pageable pageable
    ) {
        return ResponseEntity.ok(new ResponseDTO<>(1, "도서 리뷰 조회 완료되었습니다", reviewService.getReviews(bookId, ReviewStatus.ACTIVE, pageable)));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Page<ReviewNoneBookDTO>>> save(
            @Valid @RequestBody ReviewSaveRequest request,
            Errors error,
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            Pageable pageable
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        if (myUserDetails == null) {
            throw new Exception401("인증이 되지 않았습니다.");
        }
        UserDTO userDTO = userService.getUser(myUserDetails.getUser());

        Optional<Book> optionalBook = bookService.getBook(request.getBookId());
        if (optionalBook.isEmpty()) {
            throw new Exception400(BookConst.notFound);
        }

        Book book = optionalBook.get();
        Page<ReviewNoneBookDTO> reviewDTOList = reviewService.getReviews(book.getId(), ReviewStatus.ACTIVE, pageable);
        if (reviewDTOList == null) {
            throw new Exception400(ReviewConst.notFound);
        }
        reviewService.save(userDTO.toEntity(), request, optionalBook.get());

        return ResponseEntity.ok(new ResponseDTO<>(1, "리뷰 작성이 완료되었습니다.", reviewDTOList));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<ResponseDTO<Void>> delete(
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @PathVariable Integer id
    ) {
        Optional<Review> reviewOptional = reviewService.getDetail(id);
        if (reviewOptional.isEmpty()) {
            throw new Exception400("리뷰 글이 없습니다.");
        }

        Review review = reviewOptional.get();
        if (review.getUser().getId().intValue() != myUserDetails.getUser().getId()) {
            throw new Exception400("자신의 리뷰만 삭제할 수 있습니다.");
        }
        reviewService.delete(review);

        return ResponseEntity.ok(new ResponseDTO<>(1, "리뷰 삭제가 완료되었습니다.", null));
    }
}
