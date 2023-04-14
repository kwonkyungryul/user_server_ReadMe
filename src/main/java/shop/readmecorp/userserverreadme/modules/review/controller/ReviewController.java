package shop.readmecorp.userserverreadme.modules.review.controller;

import org.springframework.web.bind.annotation.RestController;
import shop.readmecorp.userserverreadme.modules.review.service.ReviewService;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
}
