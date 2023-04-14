package shop.readmecorp.userserverreadme.modules.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
