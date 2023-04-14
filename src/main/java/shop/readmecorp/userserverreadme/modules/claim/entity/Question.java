package shop.readmecorp.userserverreadme.modules.claim.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.claim.enums.ClaimStatus;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "QUESTION_TB")
public class Question extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("문의 작성한 계정 권한")
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Comment("유저")
    @ManyToOne
    private User user;

    @Comment("출판사")
    @ManyToOne
    private Publisher publisher;

    @Comment("문의 제목")
    private String title;

    @Comment("문의 내용")
    private String content;

    @Comment("문의 작성 시간")
    private LocalDateTime writeTime;

    @Comment("문의 활성화 상태")
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;
}
