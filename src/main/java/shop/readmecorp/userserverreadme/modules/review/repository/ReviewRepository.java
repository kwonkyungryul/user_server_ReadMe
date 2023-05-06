package shop.readmecorp.userserverreadme.modules.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByStatusAndBookId(ReviewStatus status, Integer book_id);

    @Query("SELECT AVG(r.stars) FROM Review r WHERE r.book.id = :bookId")
    Double findAvgStars(Integer bookId);
}
