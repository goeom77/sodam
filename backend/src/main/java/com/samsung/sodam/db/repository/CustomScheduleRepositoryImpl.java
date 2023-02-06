
package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.stereotype.Repository;

@Repository
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {
    public final JPAQueryFactory queryFactory;

    public CustomScheduleRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public ConsultSchedule makeSchedule(ScheduleRequest request) {

        return null;
    }

    public ConsultSchedule updateScheduleState(SetStateRequest request) {
        return null;
    }
}

