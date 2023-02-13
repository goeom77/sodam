package com.samsung.sodam.db.repository.schedule;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.schedule.MonthlyScheduleRequest;
import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.api.response.RemindNotiDto;
import com.samsung.sodam.api.response.schedule.MonthlyResponse;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.samsung.sodam.db.entity.QConsultApplicant.consultApplicant;
import static com.samsung.sodam.db.entity.QConsultSchedule.consultSchedule;
import static com.samsung.sodam.db.entity.QConsultSession.consultSession;
import static com.samsung.sodam.db.entity.QTroubleBoard.troubleBoard;

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
    public Page<ConsultSchedule> getMySchedules(Pageable pageable,String userId) {
         List<ConsultSchedule> list = queryFactory.selectFrom(consultSchedule).where(consultSession.counselorId.eq(userId).or(consultSession.clientId.eq(userId))).orderBy(consultSchedule.dateTime.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();
         Long count = queryFactory.select(consultSchedule.id).from(consultSchedule).where(consultSession.counselorId.eq(userId).or(consultSession.clientId.eq(userId))).orderBy(consultSchedule.dateTime.desc()).fetchOne();
        return new PageImpl<>(list, pageable,count);
    }

    @Override
    public List<ConsultApplicant> searchSchedules(SearchSchedule request) {
        return queryFactory.select(consultApplicant).from(consultApplicant).join(consultSession)
                .on(consultApplicant.sessionId.eq(consultSession.id))
                .where(
                        consultApplicant.state.eq(request.state),
                        consultSession.counselorId.eq(request.userId),
                        consultApplicant.dueDate.between(request.start,request.end)
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
    public List<RemindNotiDto> getTodayRemind() {
        return queryFactory
                .select(Projections.constructor(RemindNotiDto.class,
                        consultSchedule.dateTime,
                        consultSession.clientId,
                        consultSession.counselorId
                        )
                ).from(consultSchedule).leftJoin(consultSession)
                .on(consultSchedule.sessionId.eq(consultSession.id))
                .where(diffDate(consultSchedule.dateTime))

        return null;
    }
    private BooleanExpression diffDate(DateTimePath<LocalDateTime> scheduleDate) {
        if(scheduleDate == null || scheduleDate.equals("")) return null;
        LocalDate date = new LocalDate(scheduleDate.year(), scheduleDate.month(), scheduleDate.dayOfMonth());
    }

    @Override
    public List<RemindNotiDto> getOneHourRemind() {
        return null;
    }


}
