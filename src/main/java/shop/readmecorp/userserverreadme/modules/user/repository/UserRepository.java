package shop.readmecorp.userserverreadme.modules.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}
