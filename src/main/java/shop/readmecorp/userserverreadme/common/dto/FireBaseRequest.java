package shop.readmecorp.userserverreadme.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FireBaseRequest {
    @NotBlank(message = "idToken을 전송해주세요.")
    private String idToken;

    @NotBlank(message = "사용자 이메일을 전송해주세요.")
    private String currentUserEmail;
}
