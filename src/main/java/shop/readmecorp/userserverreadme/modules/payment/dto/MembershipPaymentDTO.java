package shop.readmecorp.userserverreadme.modules.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MembershipPaymentDTO {

    private Integer id;

    private String membershipName;

    private String membershipStartTime; // yyyy.mm.dd

    private String membershipEndTime; // yyyy.mm.dd

    private Integer price;

    private String paymentTime; // yyyy.mm.dd


}
