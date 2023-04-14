package shop.readmecorp.userserverreadme.modules.report.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.report.enums.ReportStatus;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "REPORT_TB")
public class Report extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("유저")
    @ManyToOne
    private User user;

    @Comment("리뷰")
    @ManyToOne
    private Review review;

    @Comment("신고 내용")
    private String content;

    @Comment("작성한 시간")
    private LocalDateTime writeTime;

    @Comment("신고 활성화 상태")
    @Enumerated(EnumType.STRING)
    private ReportStatus status;

}
