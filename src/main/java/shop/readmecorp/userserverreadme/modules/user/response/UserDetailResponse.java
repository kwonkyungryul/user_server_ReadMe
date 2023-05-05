package shop.readmecorp.userserverreadme.modules.user.response;

import lombok.*;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentDTO;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailResponse {
    private Integer id;

    private String username;

    private String role;

    private MembershipPaymentDTO membershipPaymentDTO;

    private Boolean isMembership;

    private Boolean isAutoPayment;

    private String joinTime;

    @Builder
    public UserDetailResponse(Integer id, String username, String role, MembershipPaymentDTO membershipPaymentDTO, Boolean isMembership, Boolean isAutoPayment, String joinTime) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.membershipPaymentDTO = membershipPaymentDTO;
        this.isMembership = isMembership;
        this.isAutoPayment = isAutoPayment;
        this.joinTime = joinTime;
    }
}
