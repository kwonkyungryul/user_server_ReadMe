package shop.readmecorp.userserverreadme.modules.history.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistorySaveRequest {

    private UserDTO user;

    private String content;

}
