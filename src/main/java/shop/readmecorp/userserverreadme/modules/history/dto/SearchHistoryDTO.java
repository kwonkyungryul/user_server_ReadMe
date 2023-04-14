package shop.readmecorp.userserverreadme.modules.history.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchHistoryDTO {

    private Integer id;

    private UserDTO user;

    private String content;

    private String status;

}
