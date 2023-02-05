package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.db.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewCustomRepository {

    //고객이 작성한 나의 후기
    Page<Review> getAllMyReview(ReviewRequest request,Pageable pageable);
    // 내가 작성한 후기
    List<Review> getMyReview(String clientId);

    List<Review> getReviewByAvg();
}
