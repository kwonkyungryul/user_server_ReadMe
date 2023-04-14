package shop.readmecorp.userserverreadme.modules.history.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryResponse {

    private Integer id;

    private UserDTO user;

    private String content;

    private String status;

}
