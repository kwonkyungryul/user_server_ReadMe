package shop.readmecorp.userserverreadme.modules.report.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewNoneBookDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReportSaveRequest {

    private UserDTO user;

    private ReviewNoneBookDTO review;

    private String content;

    private String writeTime;

}