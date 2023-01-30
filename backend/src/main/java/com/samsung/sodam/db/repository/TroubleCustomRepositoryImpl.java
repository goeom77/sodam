package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.db.entity.TroubleBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public class TroubleCustomRepositoryImpl implements TroubleCustomRepository {

    private final JPAQueryFactory queryFactory;

    public TroubleCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<TroubleBoard> getTroubleBoardList(String category, String searchWord, Pageable pageable) {
        return null;
    }
}
