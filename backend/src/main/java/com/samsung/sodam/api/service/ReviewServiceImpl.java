package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.db.entity.Review;
import com.samsung.sodam.db.repository.ReviewCustomRepository;
import com.samsung.sodam.db.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewCustomRepository reviewCustomRepository;
    @Autowired
    ReviewRepository reviewRepository;

    //고객들이 적은 나의 리뷰를 확인
    @Override
    public Page<Review> getAllMyReviews(ReviewRequest request, Pageable pageable) {
        return reviewCustomRepository.getAllMyReview(request, pageable);
    }

    @Override
    //내가 작성한 리뷰 확인(고객이)
    public List<Review> getMyReview(String clientId) {
        return reviewCustomRepository.getMyReview(clientId);
    }

    @Override
    public Review makeReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long reviewId,Review review) {
        Optional<Review> data = reviewRepository.findById(reviewId);
        if (data.isEmpty()) return null;
        Review saved = data.get();
        saved.setContents(review.getContents());
        saved.setType(review.getType());
        saved.setStars(review.getStars());

        return reviewRepository.save(saved);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getOrderByAvg() {
//        reviewRepository
        return null;
    }
}
