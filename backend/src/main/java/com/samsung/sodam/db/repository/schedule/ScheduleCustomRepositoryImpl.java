package com.samsung.sodam.db.repository.schedule;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.schedule.MonthlyScheduleRequest;
import com.samsung.sodam.api.request.schedule.ScheduleRequest;
import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.api.response.schedule.DetailSchedule;
import com.samsung.sodam.api.response.schedule.MonthlyResponse;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.samsung.sodam.db.entity.QConsultApplicant.consultApplicant;
import static com.samsung.sodam.db.entity.QConsultSchedule.consultSchedule;
import static com.samsung.sodam.db.entity.QConsultSession.consultSession;
import static com.samsung.sodam.db.entity.QCounselor.counselor;

@Repository
public class ScheduleCustomRepositoryImpl implements ScheduleCustomRepository {
    final JPAQueryFactory queryFactory;
    EntityManager em;

    public ScheduleCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<ConsultSchedule> getSchedulesByClientId(String counselorId, String clientId) {
        return null;
    }

    @Override
    public Page<ConsultSchedule> getMySchedules(Pageable pageable, String userId) {
        if(userId == null) return null;
        List<ConsultSchedule> list = queryFactory.selectFrom(consultSchedule).join(consultSession).on(consultSchedule.sessionId.eq(consultSession.id)).where(consultSession.counselorId.eq(userId)).orderBy(consultSchedule.dateTime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        if(list == null || list.isEmpty()) list = queryFactory.selectFrom(consultSchedule).join(consultSession).on(consultSchedule.sessionId.eq(consultSession.id)).where(consultSession.clientId.eq(userId)).orderBy(consultSchedule.dateTime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageImpl<>(list, pageable, 20);
    }

    @Override
    public Page<ConsultSchedule> getMySchedules(Pageable pageable, Integer sessionId) {
        List<ConsultSchedule> list = queryFactory.selectFrom(consultSchedule).join(consultSession).on(consultSchedule.sessionId.eq(consultSession.id)).where(consultSession.id.eq(sessionId)).orderBy(consultSchedule.dateTime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
        return new PageImpl<>(list, pageable, 20);
    }

    @Override
    public List<ConsultApplicant> searchSchedules(SearchSchedule request) {
        return queryFactory.select(consultApplicant).from(consultApplicant).join(consultSession)
                .on(consultApplicant.sessionId.eq(consultSession.id))
                .where(
                        consultApplicant.state.eq(request.state),
                        consultSession.counselorId.eq(request.userId),
                        consultApplicant.dueDate.between(request.start, request.end)
                ).fetch();
    }

    @Override
    public List<MonthlyResponse> getMonthlySchedule(MonthlyScheduleRequest request) {
        return queryFactory.select(
                        Projections.constructor(MonthlyResponse.class,
                                consultSchedule.id,
                                consultApplicant.name,
                                consultSchedule.dateTime,
                                consultSchedule.dateTime,
                                consultSchedule.sessionId,
                                consultSchedule.state
                        )
                ).from(consultApplicant).join(consultSchedule)
                .on(consultApplicant.sessionId.eq(consultSchedule.sessionId))
                .join(consultSession).on(consultApplicant.sessionId.eq(consultSession.id))
                .where(
                        consultSession.counselorId.eq(request.getCounselorId())
                ).fetch();
    }

    @Override
    public DetailSchedule getDetail(ScheduleRequest request) {
        return queryFactory.select(
                        Projections.constructor(DetailSchedule.class,
                                counselor.name,
                                consultApplicant.name,
                                consultSession.openviduId,
                                consultSession.id,
                                consultApplicant.email,
                                consultSession.counselorId,
                                consultSession.clientId,
                                consultSchedule.dateTime,
                                consultSchedule.turn,
                                consultApplicant.tel,
                                consultApplicant.consultType
                        )
                )
                .from(consultApplicant)
                .join(consultSchedule).on(consultApplicant.sessionId.eq(consultSchedule.sessionId))
                .join(consultSession).on(consultApplicant.sessionId.eq(consultSession.id))
                .join(counselor).on(consultSession.counselorId.eq(counselor.id))
                .where(
                        consultSession.id.eq(request.getSessionId())
                ).fetchFirst();
    }
}
