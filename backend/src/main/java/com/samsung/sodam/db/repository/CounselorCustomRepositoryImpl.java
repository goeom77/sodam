package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.response.CounselorListResponse;

import com.samsung.sodam.db.entity.Counselor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.samsung.sodam.db.entity.QConsultSession.consultSession;
import static com.samsung.sodam.db.entity.QCounselor.counselor;
import static com.samsung.sodam.db.entity.QFavoriteCounselor.favoriteCounselor;

@Repository
public class CounselorCustomRepositoryImpl implements CounselorCustomRepository {
    public final JPAQueryFactory queryFactory;

    public CounselorCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public CounselorListResponse getCounselorDetail(String id) {
        Counselor c = queryFactory.selectFrom(counselor).where(counselor.id.eq(id)).fetchOne();
        if (c == null) return null;
        return CounselorListResponse.builder().career(c.getCareer()).major(c.getMajor())
                .gender(c.getGender()).consultTypeList(c.getConsultTypeList()).tel(c.getTel())
                .profileImg(c.getProfileImg()).introduce(c.getIntroduce()).email(c.getEmail())
                .name(c.getName()).routine(c.getRoutine()).id(c.getId()).build();
    }

    public List<CounselorListResponse> getMyFavCounselor(String clientId) {
//        queryFactory.select(CounselorListResponse.builder().).from(counselor).join(favoriteCounselor).on(favoriteCounselor.clientId.eq(counselor.id)).fetch();
//        return queryFactory.select().fro
        return null;
    }
}
