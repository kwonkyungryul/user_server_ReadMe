package shop.readmecorp.userserverreadme.modules.notification.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.modules.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.notification.dto.NotificationDTO;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationStatus;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.notification.enums.OSType;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.util.DateTimeConverter;

import javax.persistence.*;

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

    @Comment("OS 타입")
    private OSType OSType;

    @Comment("알림 타입")
    private NotificationType notificationType;

    @Comment("알림 데이터")
    private String notificationData;

    @Comment("알림 받는 유저")
    @ManyToOne
    private User user;

    @Comment("알림 활성화 상태")
    @Enumerated(EnumType.STRING)
    private NotificationStatus status;

    @Builder
    public Notification(String title, String content, OSType OSType, NotificationType notificationType, String notificationData, User user, NotificationStatus status) {
        this.title = title;
        this.content = content;
        this.OSType = OSType;
        this.notificationType = notificationType;
        this.notificationData = notificationData;
        this.user = user;
        this.status = status;
    }

    public NotificationDTO toDTO() {
        return new NotificationDTO(id, title, content, notificationType.name(), notificationData, DateTimeConverter.localDateTimeToString(getCreatedDate()));
    }
}
