package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.db.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    //고객들이 적은 나의 리뷰를 확인
    Page<Review> getAllMyReviews(ReviewRequest request,Pageable pageable);
    //내가 작성한 리뷰 확인(고객이)
    List<Review> getMyReview(String clientId);

    Review makeReview(Review review);

    Review updateReview(Long reviewId,Review review);

    void deleteReview(Long id);

    List<Review> getOrderByAvg();
}
