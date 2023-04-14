package shop.readmecorp.userserverreadme.modules.review.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.review.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
}
