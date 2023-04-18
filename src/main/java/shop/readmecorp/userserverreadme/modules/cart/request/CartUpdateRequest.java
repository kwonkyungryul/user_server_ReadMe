package shop.readmecorp.userserverreadme.modules.cart.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateRequest {

    @NotBlank(message = "유저가 없습니다.")
    private UserDTO user;

    @NotBlank(message = "책이 없습니다.")
    private BookDTO book;

    private String status;


}
