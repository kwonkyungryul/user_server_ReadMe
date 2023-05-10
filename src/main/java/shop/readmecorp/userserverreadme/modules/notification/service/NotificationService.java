package shop.readmecorp.userserverreadme.modules.notification.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.modules.notification.dto.NotificationDTO;
import shop.readmecorp.userserverreadme.modules.notification.entity.Notification;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationStatus;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationType;
import shop.readmecorp.userserverreadme.modules.notification.enums.OSType;
import shop.readmecorp.userserverreadme.modules.notification.repository.NotificationRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.repository.UserFcmRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserFcmRepository userFcmRepository;

    public NotificationService(NotificationRepository notificationRepository, UserFcmRepository userFcmRepository) {
        this.notificationRepository = notificationRepository;
        this.userFcmRepository = userFcmRepository;
    }

    public List<NotificationDTO> getNotificationList(MyUserDetails myUserDetails, OSType osType) {
        return notificationRepository.findByStatusNotAndUserAndOSType(NotificationStatus.DELETE, myUserDetails.getUser(), osType)
                .stream()
                .map(Notification::toDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public List<NotificationDTO> save (
        String content,
        NotificationType notificationType,
        String notificationData, User user
    ) {
        List<NotificationDTO> notificationList = new ArrayList<>();
        userFcmRepository.findByUser(user).forEach(
            userFcm -> {
                // TODO FCM 메세지 전송 후 성공 시 데이터 저장

                notificationList.add(
                    notificationRepository.save(
                        new Notification(
                                null, content, userFcm.getOsType(), notificationType, notificationData,
                                user, NotificationStatus.ACTIVE
                        )).toDTO()
                );
            }
        );
        return notificationList;
    }
}
