package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.ReviewRequest;
import com.samsung.sodam.db.entity.QReview;
import com.samsung.sodam.db.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.samsung.sodam.db.entity.QReview.review;

@Repository
public class ReviewCustomRepositoryImpl implements ReviewCustomRepository {
    private JPAQueryFactory queryFactory;

    @Override
    public Page<Review> getAllMyReview(ReviewRequest request, Pageable pageable) {
        List<Review> list = queryFactory.selectFrom(review)
                .where(review.counselorId.eq(request.getCounselorId())).fetch();

        return new PageImpl<>(list, pageable, list.size());
    }

    @Override
    public List<Review> getMyReview(String clientId) {
        return queryFactory.selectFrom(review)
                .where(review.clientId.eq(clientId)).fetch();
    }
}
