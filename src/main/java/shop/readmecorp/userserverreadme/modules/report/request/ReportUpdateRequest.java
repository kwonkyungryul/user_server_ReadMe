package shop.readmecorp.userserverreadme.modules.report.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportUpdateRequest {

    private UserDTO user;

    private ReviewDTO review;

    private String content;

    private String writeTime;

    private String status;
}
