package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.db.entity.ConsultApplicant;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.samsung.sodam.db.entity.QConsultApplicant.consultApplicant;
import static com.samsung.sodam.db.entity.QConsultSession.consultSession;

@Repository
public class ApplicantCustomRepositoryImpl implements ApplicantCustomRepository {
    public final JPAQueryFactory queryFactory;

    public ApplicantCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public List<ConsultApplicant> getMyApplicants(String CounselorId) {
        return queryFactory.selectFrom(consultApplicant)
                .join(consultSession).on(consultSession.id.eq(consultApplicant.sessionId))
                .where(consultSession.counselorId.eq(CounselorId)).fetch();
    }
}
