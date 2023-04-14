package shop.readmecorp.userserverreadme.modules.notification.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import shop.readmecorp.userserverreadme.common.jpa.BaseTime;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

}
