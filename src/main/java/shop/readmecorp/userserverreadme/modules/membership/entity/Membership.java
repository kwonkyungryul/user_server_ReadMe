package shop.readmecorp.userserverreadme.modules.membership.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.membership.enums.MembershipStatus;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBERSHIP_TB")
public class Membership extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("멤버십 가격")
    private Integer price;

    @Comment("멤버십 기간")
    private String membershipTerm;

    @Comment("멤버십 활성화 상태")
    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

}
