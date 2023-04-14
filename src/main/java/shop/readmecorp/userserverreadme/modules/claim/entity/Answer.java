package shop.readmecorp.userserverreadme.modules.claim.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.claim.enums.ClaimStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ANSWER_TB")
public class Answer extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("출판사의 문의")
    @OneToOne
    private Question question;

    @Comment("답변 내용")
    private String content;

    @Comment("답변 작성 시간")
    private LocalDateTime writeTime;

    @Comment("답변 활성화 상태")
    @Enumerated(EnumType.STRING)
    private ClaimStatus status;
}
