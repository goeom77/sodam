package com.samsung.sodam.db.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.db.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.samsung.sodam.db.entity.QReview.review;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    public final JPAQueryFactory queryFactory;

    public ReviewCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Review> getAllMyReview(ReviewRequest request, Pageable pageable) {
        List<Review> list = new ArrayList<>();
        if (checkIsNullOrEmpty(request.getClientId()) && (checkIsNullOrEmpty(request.getType()) || request.getType().equals("ALL"))) {
            list = queryFactory.selectFrom(review)
                    .where(review.counselorId.eq(request.getCounselorId())).fetch();
            return new PageImpl<>(list, pageable, list.size());
        } else if (checkIsNullOrEmpty(request.getClientId())) {

            list = queryFactory.selectFrom(review)
                    .where(review.counselorId.eq(request.getCounselorId()),review.type.like(request.getType())).fetch();
            return new PageImpl<>(list, pageable, list.size());
        } else if (checkIsNullOrEmpty(request.getType()) || request.getType().equals("ALL")) {
            list = queryFactory.selectFrom(review)
                    .where(review.counselorId.eq(request.getCounselorId()),review.clientId.like(request.getClientId())).fetch();
            return new PageImpl<>(list, pageable, list.size());
        }


        return new PageImpl<>(list, pageable, 0);
    }

    @Override
    public List<Review> getMyReview(String clientId) {
        return queryFactory.selectFrom(review)
                .where(review.clientId.eq(clientId)).fetch();
    }

    @Override
    public List<Review> getReviewByAvg() {
//        return queryFactory.select(review,review.stars.avg().as("average")).from(review).groupBy(review.counselorId).fetch();
//
        return null;
    }


    public boolean checkIsNullOrEmpty(String input) {
        return input == null || input.isEmpty() || input.isBlank();
    }
}
