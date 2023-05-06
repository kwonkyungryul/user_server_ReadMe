package shop.readmecorp.userserverreadme.modules.payment.dto;

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
public class MembershipPaymentNoneUserDTO {

    private Integer id;

    private MembershipDTO membership;

    private String membershipStartTime;

    private String membershipEndTime;

    private Integer price;

    private String paymentTime;

}
