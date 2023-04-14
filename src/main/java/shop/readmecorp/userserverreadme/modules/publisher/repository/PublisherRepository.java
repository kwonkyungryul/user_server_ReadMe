package shop.readmecorp.userserverreadme.modules.publisher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.publisher.entity.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
