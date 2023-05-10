package shop.readmecorp.userserverreadme.modules.cart.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.common.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.book.BookConst;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.book.service.BookService;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.response.CartResponse;
import shop.readmecorp.userserverreadme.modules.cart.service.CartService;
import shop.readmecorp.userserverreadme.modules.payment.dto.BookPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.service.BookPaymentService;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;
    private final BookService bookService;
    private final UserService userService;
    private final BookPaymentService bookPaymentService;

    public CartController(CartService cartService, BookService bookService, UserService userService, BookPaymentService bookPaymentService) {
        this.cartService = cartService;
        this.bookService = bookService;
        this.userService = userService;
        this.bookPaymentService = bookPaymentService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<CartDTO>>> getCartByUserId (
            @AuthenticationPrincipal MyUserDetails myUserDetails
    ){
        return ResponseEntity.ok(new ResponseDTO<>(1, "유저 장바구니 조회 성공", cartService.getCartList(myUserDetails.getUser())));
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<CartResponse>> saveCart (
            @AuthenticationPrincipal MyUserDetails myUserDetails,
            @Valid @RequestBody CartSaveRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        Optional<Book> optionalBook = bookService.getBook(request.getBookId());
        if (optionalBook.isEmpty()) {
            throw new Exception400("책 정보를 찾을 수 없습니다.");
        }

        if (cartService.isCart(optionalBook.get(), myUserDetails.getUser())) {
            throw new Exception400("이미 장바구니에 있는 책입니다.");
        }

        List<Integer> myBookIds = bookPaymentService.getMyList(myUserDetails.getUser())
                                                    .stream()
                                                    .map(BookPaymentDTO::getId)
                                                    .collect(Collectors.toList());

        if (myBookIds.contains(request.getBookId())) {
            throw new Exception400("이미 구매한 도서가 존재합니다.");
        }

        UserDTO userDTO = userService.getUser(myUserDetails.getUser());
        return ResponseEntity.ok(new ResponseDTO<>(1, "장바구니 등록 성공", cartService.save(userDTO.toEntity(), optionalBook.get())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart (
        @PathVariable Integer id,
        @AuthenticationPrincipal MyUserDetails myUserDetails
    ) {
        Optional<Cart> cartOptional = cartService.getCart(id);
        if (cartOptional.isEmpty() || cartOptional.get().getUser().getId().intValue() != myUserDetails.getUser().getId()) {
            throw new Exception400("내 장바구니 내역이 아닙니다.");
        }
        cartService.delete(id);
        return ResponseEntity.ok(new ResponseDTO<>(1, "삭제가 완료되었습니다.", null));
    }

}
