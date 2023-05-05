package shop.readmecorp.userserverreadme.modules.user.response;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentNoneUserDTO;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailResponse {
    private Integer id;

    private String username;

    private String role;

    private MembershipPaymentNoneUserDTO membershipPaymentNoneUserDTO;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    private String joinTime;

    @Builder
    public UserDetailResponse(Integer id, String username, String role, MembershipPaymentNoneUserDTO membershipPaymentNoneUserDTO, Boolean isMembership, Boolean isAutoPayment, String joinTime) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.membershipPaymentNoneUserDTO = membershipPaymentNoneUserDTO;
        this.isMembership = isMembership;
        this.isAutoPayment = isAutoPayment;
        this.joinTime = joinTime;
    }
}
