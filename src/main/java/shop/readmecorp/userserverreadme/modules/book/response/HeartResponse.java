package shop.readmecorp.userserverreadme.modules.book.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.book.dto.BookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeartResponse {

    private Integer id;

    private UserDTO user;

    private BookDTO book;

    private String status;

}
