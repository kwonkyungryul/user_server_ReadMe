package shop.readmecorp.userserverreadme.modules.claim.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.publisher.dto.PublisherDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

    private Integer id;

    private String role;

    private UserDTO user;

    private PublisherDTO publisher;

    private String title;

    private String content;

    private String writeTime;


}
