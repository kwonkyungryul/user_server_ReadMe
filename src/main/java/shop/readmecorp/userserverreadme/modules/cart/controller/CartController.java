package shop.readmecorp.userserverreadme.modules.cart.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.readmecorp.userserverreadme.common.exception.Exception400;
import shop.readmecorp.userserverreadme.modules.book.dto.ResponseDTO;
import shop.readmecorp.userserverreadme.modules.cart.CartConst;
import shop.readmecorp.userserverreadme.modules.cart.dto.CartDTO;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.request.CartSaveRequest;
import shop.readmecorp.userserverreadme.modules.cart.request.CartUpdateRequest;
import shop.readmecorp.userserverreadme.modules.cart.response.CartResponse;
import shop.readmecorp.userserverreadme.modules.cart.service.CartService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping
    public ResponseEntity<Page<CartDTO>> getPage(Pageable pageable) {
        var page = cartService.getPage(pageable);
        var content = page.getContent()
                .stream()
                .map(Cart::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new PageImpl<>(content, pageable, page.getTotalElements())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponse> getCart (@PathVariable Integer id) {
        var optionalCart = cartService.getCart(id);
        if (optionalCart.isEmpty()) {
            throw new Exception400(CartConst.notFound);
        }

        return ResponseEntity.ok(
                optionalCart.get().toResponse()
        );
    }

    @GetMapping("/{userId}/users")
    public ResponseEntity<?> getCartByUserId (@PathVariable Integer userId){

        return ResponseEntity.ok(new ResponseDTO<>(1, "유저 장바구니 조회 성공", cartService.getCartByUserId(userId)));
    }

    @PostMapping
    public ResponseEntity<?> saveCart (
            @Valid @RequestBody CartSaveRequest request,
            Errors error
    ) {
        if (error.hasErrors()) {
            throw new Exception400(error.getAllErrors().get(0).getDefaultMessage());
        }

        var cart = cartService.save(request);
        return ResponseEntity.ok(new ResponseDTO<>(1, "장바구니 등록 성공", cart));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart (
            @PathVariable Integer id
    ) {
        cartService.delete(id);

        return ResponseEntity.ok(new ResponseDTO<>(1, "삭제가 완료되었습니다.", null));
    }
}
