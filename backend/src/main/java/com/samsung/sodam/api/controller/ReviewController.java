package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.api.service.ReviewService;
import com.samsung.sodam.db.entity.Review;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService service;
    private final ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.service = reviewService;
        this.reviewService = reviewService;
    }

    @PostMapping("search")
    public List<Review> getReviewAvg(){
        return service.getOrderByAvg();
    }

    //상담사의 후기 목록 보기
    @PostMapping("/my/counselor")
    @ApiOperation(value = "고객이 작성한 나의 후기 목록 보기")
    public Page<Review> getAllMyReviews(@RequestBody ReviewRequest request, @PageableDefault(size=20, sort="id") Pageable pageable) {
        return reviewService.getAllMyReviews(request,pageable);
    }

    @GetMapping("search")
    @ApiOperation(value = "리뷰pk로 리뷰 내용 조회")
    public Review getReviewById(Long id){
        return service.getReviewById(id);
    }

    //내가 작성한 후기 목록 보기
    @GetMapping("my")
    @ApiOperation(value = "내가 작성한 후기 목록 보기")
    public List<Review> getReviews(@NotNull String clientId) {
        return reviewService.getMyReview(clientId);
    }

    @PostMapping("")
    @ApiOperation(value = "후기 작성하기")
    public Review makeReview(@RequestBody Review review){
        return reviewService.makeReview(review);
    }

    @PostMapping("/{reviewId}")
    @ApiOperation(value = "후기 수정하기")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody Review review){
        return reviewService.updateReview(reviewId,review);
    }

    @DeleteMapping("review")
    @ApiOperation("리뷰 삭제")
    public void deleteReview(Long reviewId){
        reviewService.deleteReview(reviewId);
    }
}
