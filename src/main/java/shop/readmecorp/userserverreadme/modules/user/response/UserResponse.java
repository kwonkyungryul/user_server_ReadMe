package shop.readmecorp.userserverreadme.modules.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String username;

    private String role;

    private Boolean isMembership;

    private Boolean isAutoPayment;

}
