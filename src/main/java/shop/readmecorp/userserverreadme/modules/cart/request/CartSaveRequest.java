package shop.readmecorp.userserverreadme.modules.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.book.entity.Book;
import shop.readmecorp.userserverreadme.modules.cart.entity.Cart;
import shop.readmecorp.userserverreadme.modules.cart.enums.CartStatus;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartSaveRequest {

    @NotNull(message = "유저 정보를 입력해주세요.")
    private UserDTO user;

    @NotNull(message = "책 정보를 입력해주세요.")
    private BookDTO book;

    public Cart toEntity() {
        return Cart.builder()
                .id(null)
                .user(user.toEntity())
                .book(book.toEntity())
                .build();
    }
}
