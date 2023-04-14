package shop.readmecorp.userserverreadme.modules.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.notification.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}
