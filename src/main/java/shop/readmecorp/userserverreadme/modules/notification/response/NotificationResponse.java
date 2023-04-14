package shop.readmecorp.userserverreadme.modules.notification.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private Integer id;

    private String title;

    private String content;

    private String writeTime;

    private String status;
}
