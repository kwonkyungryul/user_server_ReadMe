package shop.readmecorp.userserverreadme.modules.common.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FirebaseRequest {
    @NotBlank(message = "idToken을 전송해주세요.")
    private String idToken;
}
