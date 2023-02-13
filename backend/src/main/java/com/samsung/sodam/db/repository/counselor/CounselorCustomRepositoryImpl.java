package com.samsung.sodam.db.repository.counselor;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.api.request.TestRequest;
import com.samsung.sodam.api.response.*;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.GENDER;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.querydsl.core.types.dsl.Wildcard.count;
import static com.samsung.sodam.db.entity.Counselor.toResponse;
import static com.samsung.sodam.db.entity.QCertificate.certificate;
import static com.samsung.sodam.db.entity.QCounselor.counselor;
import static com.samsung.sodam.db.entity.QEducation.education;
import static com.samsung.sodam.db.entity.QFavoriteCounselor.favoriteCounselor;
import static com.samsung.sodam.db.entity.QReview.review;

@Repository
public class CounselorCustomRepositoryImpl implements CounselorCustomRepository {
    public JPAQueryFactory queryFactory;

    public EntityManager em;

    public CounselorCustomRepositoryImpl(JPAQueryFactory queryFactory, EntityManager em) {
        this.queryFactory = queryFactory;
        this.em = em;
    }

    public CounselorListResponse getCounselorDetail(String id) {
        Counselor c = queryFactory.selectFrom(counselor).where(counselor.id.eq(id)).fetchOne();
        if (c == null) return null;
        return CounselorListResponse.builder().career(c.getCareer())
                .gender(c.getGender()).consultTypeList(c.getConsultTypeList()).tel(c.getTel())
                .profileImg(c.getProfileImg()).introduce(c.getIntroduce()).email(c.getEmail())
                .name(c.getName()).routine(c.getRoutine()).id(c.getId()).build();
    }

    public List<CounselorListResponse> getMyFavCounselor(String clientId) {
//        queryFactory.select(CounselorListResponse.builder().).from(counselor).join(favoriteCounselor).on(favoriteCounselor.clientId.eq(counselor.id)).fetch();
        List<Counselor> c = queryFactory.selectFrom(counselor).join(favoriteCounselor).on(counselor.id.eq(favoriteCounselor.CounselorId)).where(favoriteCounselor.clientId.eq(clientId)).distinct().fetch();
        return c.stream().map(it -> CounselorListResponse.builder()
                .career(it.getCareer())
                .consultTypeList(it.getConsultTypeList())
                .email(it.getEmail())
                .gender(it.getGender())
                .id(it.getId())
                .introduce(it.getIntroduce())
                .name(it.getName())
                .profileImg(it.getProfileImg())
                .routine(it.getRoutine())
                .tel(it.getTel()).build()).collect(Collectors.toList());

    }

    /**
     * 상담사용 프로필 보기 (마이페이지)
     *
     * @param id 상담사 아이디
     * @return 프로필 + 자격증 + 학력사항
     */
    @Transactional
    public CounselorDetailResponse getCounselorDetailAll(String id) {
        Counselor c = queryFactory.selectFrom(counselor).where(counselor.id.eq(id)).fetchOne();
        if (c == null) return null;

        CounselorDetailResponse result = new CounselorDetailResponse(getCounselorDetail(id));

        List<CertificateResponse> certList = queryFactory
                .select(new QCertificateResponse(
                        certificate.id,
                        certificate.name,
                        certificate.serial_num,
                        certificate.agency,
                        certificate.photo
                )).from(certificate)
                .innerJoin(certificate.counselor, counselor)
                .where(certificate.counselor.id.eq(id))
                .fetch();

        List<EducationResponse> eduList = queryFactory
                .select(new QEducationResponse(
                        education.id,
                        education.degree,
                        education.school,
                        education.major,
                        education.is_graduate,
                        education.photo
                )).from(education)
                .innerJoin(education.counselor, counselor)
                .where(education.counselor.id.eq(id))
                .fetch();

        result.setCertificate(certList);
        result.setEducation(eduList);
        System.out.println(result);
        return result;
    }

    /**
     * 검색 조건에 맞는 상담사 조회
     **/
    @Override
    public Page<CounselorListResponse> searchCounselor(CounselorSearchRequest request, Pageable pageable) {
//        int pageNumber = pageable.getPageNumber();
//        int pageSize = pageable.getPageSize();
//
//        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//        CriteriaQuery<Counselor> criteriaQuery = criteriaBuilder.createQuery(Counselor.class);
//        Root<Counselor> itemRoot = criteriaQuery.from(Counselor.class);
//        CriteriaQuery<Counselor> select = criteriaQuery.select(itemRoot);
//        TypedQuery<Counselor> typedQuery = em.createQuery(select);
//        Predicate likeName = criteriaBuilder.like(itemRoot.get("name"), request.getKeyword());
//        Predicate likeIntroduce =  criteriaBuilder.like(itemRoot.get("introduce"), request.getKeyword());
//        Subquery subquery = criteriaQuery.subquery(CONSULT_TYPE.class);
//        Predicate matchType = criteriaBuilder.in(itemRoot.get("consultTypeList")).value(subquery);


//        if (request.getGender() != null && Arrays.stream(GENDER.values()).toList().contains(request.getGender())) {
//            query.where(counselor.introduce.like(request.getKeyword()));
//        }
//
//        else if (request.getType() != null && Arrays.stream(CONSULT_TYPE.values()).toList().contains(request.getType())) {
//            query.where(counselor.consultTypeList.contains(request.getType()));
//        }
//        else {
//            JPAQuery<Counselor> query = queryFactory.
//                    select(counselor)
//                    .from(counselor)
//                    .where(counselor.name.contains("%" + request.getKeyword() + "%")
//                            ,counselor.introduce.like(request.getKeyword())
//                            ,counselor.consultTypeList.contains(request.getType()));
//        }
//        Predicate finalPredicate = criteriaBuilder.and(likeName,likeIntroduce,matchType);
//        criteriaQuery.where(finalPredicate);
//        int total = em.createQuery(criteriaQuery).getMaxResults();
////
//
//        List<CounselorListResponse> result = em.createQuery(criteriaQuery).setFirstResult((pageNumber-1)*pageSize).getResultList();

        List<Counselor> list = queryFactory.
                select(counselor)
                .from(counselor)
                .where(counselor.name.like("%" + request.getKeyword() + "%")).fetch();
//                        , counselor.introduce.like(request.getKeyword())
//                        , counselor.consultTypeList.contains(request.getType()))

//        Long total = queryFactory.select(Wildcard.count)
//                .from(counselor)
//                .where(counselor.name.contains("%" + request.getKeyword() + "%")
//                        , counselor.introduce.like(request.getKeyword())
//                        , counselor.consultTypeList.contains(request.getType())).fetchOne();
        return new PageImpl<>(list.stream().map(Counselor::toResponse).collect(Collectors.toList()), Pageable.ofSize(pageable.getPageSize()), list.size());
    }

    @Override
    public Page<CounselorListResponse> searchCounselorByType(TestRequest request) {

//        List<Counselor> list = queryFactory.
//                select(counselor)
//                .from(counselor)
//                .where(counselor.name.like("%" + request.getKeyword() + "%")).fetch();

//        return queryFactory
//                .selectFrom(counselor)
//                .from(counselor)
//                .where(counselor.consultTypeList.in())
//                .fetch();

        return null;
    }

    private Expression[] searchTypesIn(List<CONSULT_TYPE> types) {

        List<Expression> tuples = new ArrayList<>();

        for(CONSULT_TYPE type : types) {
            tuples.add(Expressions.template(Object.class, "({0})", type));
        }

        return tuples.toArray(new Expression[0]);
    }

    @Override
    public Page<CounselorListResponse> getBestCounselor(Pageable pageable) {

        List<Tuple> list = queryFactory.select(counselor, review.stars.avg().as("points"))
                .from(counselor)
                .join(review)
                .on(counselor.id.eq(review.counselorId))
                .groupBy(counselor.id).fetch();



//        list.stream().map(it -> CounselorListResponse.builder()
//                .career(it.getCareer())
//                .consultTypeList(it.getConsultTypeList())
//                .email(it.getEmail())
//                .gender(it.getGender())
//                .id(it.getId())
//                .introduce(it.getIntroduce())
//                .name(it.getName())
//                .profileImg(it.getProfileImg())
//                .routine(it.getRoutine())
//                .tel(it.getTel()).build()).collect(Collectors.toList());

        System.out.println("tuples:" + list.toString());
        return null;
    }
}
