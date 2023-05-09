package shop.readmecorp.userserverreadme.modules.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Page<Review> findByStatusAndBookId(ReviewStatus status, Integer bookId, Pageable pageable);
    List<Review> findByStatusAndUser(ReviewStatus status, User user);
    Optional<Review> findByStatusAndId(ReviewStatus status, Integer id);

    @Query("SELECT AVG(r.stars) FROM Review r WHERE r.book.id = :bookId")
    Double findAvgStars(Integer bookId);
}
