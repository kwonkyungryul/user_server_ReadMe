package shop.readmecorp.userserverreadme.modules.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewNoneBookDTO {
    private Integer id;

    private UserDTO user;

    private Double stars;

    private String content;

    private String writeTime;
}
