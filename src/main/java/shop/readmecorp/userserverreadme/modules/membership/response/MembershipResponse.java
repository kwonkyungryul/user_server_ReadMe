package shop.readmecorp.userserverreadme.modules.membership.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {

    private Integer id;

    private Integer price;

    private String membershipTerm;

    private String status;

}
