package shop.readmecorp.userserverreadme.modules.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDTO {

    private Integer id;

    private String title;

    private String content;

    private String notificationType;

    private String notificationData;

    private String writeTime;
}
