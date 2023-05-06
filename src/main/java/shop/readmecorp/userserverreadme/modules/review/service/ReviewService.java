package shop.readmecorp.userserverreadme.modules.review.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.review.dto.ReviewDTO;
import shop.readmecorp.userserverreadme.modules.review.entity.Review;
import shop.readmecorp.userserverreadme.modules.review.enums.ReviewStatus;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> getReviews(Integer bookID, ReviewStatus status) {
        return reviewRepository.findByStatusAndBookId(status, bookID)
                .stream().map(Review::toDTO)
                .collect(Collectors.toList());
    }

}
