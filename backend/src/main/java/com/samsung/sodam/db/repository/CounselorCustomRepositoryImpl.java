package com.samsung.sodam.db.repository;

import com.google.gson.Gson;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.TestRequest;
import com.samsung.sodam.api.response.CounselorListResponse;

import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

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
        List<Counselor> c =queryFactory.selectFrom(counselor).join(favoriteCounselor).on(counselor.id.eq(favoriteCounselor.CounselorId)).where(favoriteCounselor.clientId.eq(clientId)).distinct().fetch();
        return c.stream().map(it -> CounselorListResponse.builder()
                .career(it.getCareer())
                .consultTypeList(it.getConsultTypeList())
                .email(it.getEmail())
                .gender(it.getGender())
                .id(it.getId())
                .introduce(it.getIntroduce())
                .major(it.getMajor())
                .name(it.getName())
                .profileImg(it.getProfileImg())
                .routine(it.getRoutine())
                .tel(it.getTel()).build()).collect(Collectors.toList());

    }

    @Transactional
    public Long updateType(TestRequest request, String clientId){
        System.out.println(request.getList());
        return queryFactory.update(counselor)
                .where(counselor.id.eq(clientId))
                .set(counselor.consultTypeList,request.getList())
                .execute();
    }

    public String convertToDatabaseColumn(List<CONSULT_TYPE> attribute) {
        if (attribute == null || attribute.isEmpty()) return null;
        List<Integer> list = attribute.stream().map(CONSULT_TYPE::getValue).collect(Collectors.toList());
        System.out.println("toJson:: "+list.toString());
        return new Gson().toJson(list);
    }
}
