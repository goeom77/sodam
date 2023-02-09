package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EducationCustomRepository {
    public final JPAQueryFactory queryFactory;

    public EducationCustomRepository(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


}
