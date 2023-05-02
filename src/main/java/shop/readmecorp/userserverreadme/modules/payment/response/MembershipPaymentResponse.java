package shop.readmecorp.userserverreadme.modules.payment.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.membership.dto.MembershipDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipPaymentResponse {

    private Integer id;

    private UserDTO user;

    private MembershipDTO membership;

    private String membershipStartTime;

    private String membershipEndTime;

    private Integer price;

    private String paymentTime;

    private String status;

}
