package shop.readmecorp.userserverreadme.modules.report.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.report.dto.ReportDTO;
import shop.readmecorp.userserverreadme.modules.report.enums.ReportStatus;
import shop.readmecorp.userserverreadme.modules.report.response.ReportResponse;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
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

    @Builder
    public Report(Integer id, User user, Review review, String content, LocalDateTime writeTime, ReportStatus status) {
        this.id = id;
        this.user = user;
        this.review = review;
        this.content = content;
        this.writeTime = writeTime;
        this.status = status;
    }

    public ReportDTO toDTO() {
        return new ReportDTO(id, user.toDTO(), review.toDTO(), content, writeTime.toString(), status.name());
    }

    public ReportResponse toResponse() {
        return new ReportResponse(id, user.toDTO(), review.toDTO(), content, writeTime.toString(), status.name());
    }
}
