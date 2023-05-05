package shop.readmecorp.userserverreadme.modules.user.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.file.dto.FileInfoDTO;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Integer id;

    private String username;

    private String role;

    private MembershipPaymentDTO membershipPaymentInfo;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    private String joinTime;

    private String status;

}
