package shop.readmecorp.userserverreadme.modules.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.payment.dto.MembershipPaymentNoneUserDTO;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDTO {
    private Integer id;

    private String username;

    private MembershipPaymentNoneUserDTO membershipPaymentNoneUserDTO;

    @Builder
    public UserInfoDTO(Integer id, String username, MembershipPaymentNoneUserDTO membershipPaymentNoneUserDTO) {
        this.id = id;
        this.username = username;
        this.membershipPaymentNoneUserDTO = membershipPaymentNoneUserDTO;
    }
}
