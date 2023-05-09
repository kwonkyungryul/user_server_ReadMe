package shop.readmecorp.userserverreadme.modules.payment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.membership.dto.MembershipDTO;
import shop.readmecorp.userserverreadme.modules.user.dto.UserDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipPaymentSaveRequest {

    private Integer membershipId;

    private String membershipStartTime;

    private Integer price;

    private String paymentTime;

}
