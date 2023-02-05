package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.service.ReviewService;
import com.samsung.sodam.db.entity.Review;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService service;

    public ReviewController(ReviewService reviewService) {
        this.service = reviewService;
    }

    @PostMapping("review/search")
    public List<Review> getReviewAvg(){
        return service.getOrderByAvg();
    }
}
