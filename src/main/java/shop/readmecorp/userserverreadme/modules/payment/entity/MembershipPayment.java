package shop.readmecorp.userserverreadme.modules.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.card.entity.Card;
import shop.readmecorp.userserverreadme.modules.membership.entity.Membership;
import shop.readmecorp.userserverreadme.modules.payment.enums.PaymentStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBERSHIP_PAYMENT_TB")
public class MembershipPayment extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("리뷰 작성한 유저")
    @ManyToOne
    private User user;

    @Comment("멤버십 정보")
    @OneToOne
    private Membership membership;

    @Comment("멤버십 시작 시간")
    private String membershipStartTime;

    @Comment("멤버십 종료 시간")
    private String membershipEndTime;

    @Comment("멤버십 가격")
    private Integer price;

    @Comment("결제한 카드")
    @ManyToOne
    private Card card;

    @Comment("구매한 시간")
    private LocalDateTime paymentTime;

    @Comment("멤버십 구매내역 활성화 상태")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
}

