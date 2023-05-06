package shop.readmecorp.userserverreadme.common.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticeTypeWrapper {
    private String noticeType;
}
