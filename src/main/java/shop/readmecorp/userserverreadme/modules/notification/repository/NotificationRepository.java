package shop.readmecorp.userserverreadme.modules.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.notification.entity.Notification;
import shop.readmecorp.userserverreadme.modules.notification.enums.NotificationStatus;
import shop.readmecorp.userserverreadme.modules.notification.enums.OSType;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    List<Notification> findByStatusNotAndUserAndOSType(NotificationStatus status, User user, OSType OSType);
}
