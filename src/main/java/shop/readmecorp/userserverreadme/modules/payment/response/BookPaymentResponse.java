package shop.readmecorp.userserverreadme.modules.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookPaymentResponse {

    private Integer id;

    private UserDTO user;

    private BookDTO book;

    private Integer price;

    private String paymentTime;

    private String status;

}
