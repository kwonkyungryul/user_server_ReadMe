package shop.readmecorp.userserverreadme.modules.claim.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.claim.dto.QuestionDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerSaveRequest {

    private QuestionDTO question;

    private String content;

    private String writeTime;

}
