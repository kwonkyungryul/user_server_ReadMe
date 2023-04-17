package shop.readmecorp.userserverreadme.modules.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.book.entity.Heart;

public interface HeartRepository extends JpaRepository<Heart, Integer> {
}
