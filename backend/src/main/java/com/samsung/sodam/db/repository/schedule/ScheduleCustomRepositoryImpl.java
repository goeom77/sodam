package com.samsung.sodam.db.repository.schedule;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.SearchSchedule;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;

import static com.samsung.sodam.db.entity.QConsultApplicant.consultApplicant;
import static com.samsung.sodam.db.entity.QConsultSchedule.consultSchedule;
import static com.samsung.sodam.db.entity.QConsultSession.consultSession;

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
    public Page<ConsultSchedule> getMySchedules(String userId) {
//        queryFactory.selectFrom(consultSchedule).where(consultSchedule.i)

        return null;
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

}
