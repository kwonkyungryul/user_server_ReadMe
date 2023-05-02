package shop.readmecorp.userserverreadme.modules.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByBookId(Integer bookId);

    @Query("SELECT AVG(r.stars) FROM Review r WHERE r.book.id = :bookId")
    Double findAvgStars(Integer bookId);
}
