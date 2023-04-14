package shop.readmecorp.userserverreadme.modules.heart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.heart.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
}
