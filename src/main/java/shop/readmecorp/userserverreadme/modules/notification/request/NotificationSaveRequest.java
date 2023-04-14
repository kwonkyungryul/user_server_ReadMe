package shop.readmecorp.userserverreadme.modules.notification.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationSaveRequest {

    private String title;

    private String content;

    private String writeTime;

}
