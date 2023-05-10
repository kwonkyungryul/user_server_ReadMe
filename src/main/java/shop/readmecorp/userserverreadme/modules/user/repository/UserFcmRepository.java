package shop.readmecorp.userserverreadme.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;
import shop.readmecorp.userserverreadme.modules.user.entity.UserFcm;

import java.util.List;

public interface UserFcmRepository extends JpaRepository<UserFcm, Integer> {
    List<UserFcm> findByUser(User user);
}
