package shop.readmecorp.userserverreadme.modules.notification.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.notification.dto.NotificationDTO;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationStatus;
import shop.readmecorp.userserverreadme.modules.notification.response.NotificationResponse;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "NOTIFICATION_TB")
public class Notification extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("고유번호")
    private Integer id;

    @Comment("알림 제목")
    private String title;

    @Comment("알림 내용")
    private String content;

    @Comment("알림 작성 시간")
    private LocalDateTime writeTime;

    @Comment("알림 활성화 상태")
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Builder
    public Notification(Integer id, String title, String content, LocalDateTime writeTime, NotificationStatus status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writeTime = writeTime;
        this.status = status;
    }

    public NotificationDTO toDTO() {
        return new NotificationDTO(id, title, content,writeTime.toString(), status.name());
    }

    public NotificationResponse toResponse() {
        return new NotificationResponse(id, title, content,writeTime.toString(), status.name());
    }
}
