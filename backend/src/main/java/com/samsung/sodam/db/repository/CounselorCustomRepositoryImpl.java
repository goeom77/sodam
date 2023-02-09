package com.samsung.sodam.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.samsung.sodam.api.response.*;
import com.samsung.sodam.db.entity.Counselor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.samsung.sodam.db.entity.QCertificate.certificate;
import static com.samsung.sodam.db.entity.QCounselor.counselor;
import static com.samsung.sodam.db.entity.QEducation.education;
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
        return CounselorListResponse.builder().career(c.getCareer())
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
                .name(it.getName())
                .profileImg(it.getProfileImg())
                .routine(it.getRoutine())
                .tel(it.getTel()).build()).collect(Collectors.toList());

    }

    /**
     * 상담사용 프로필 보기 (마이페이지)
     * @param id 상담사 아이디
     * @return 프로필 + 자격증 + 학력사항 */
    @Transactional
    public CounselorDetailResponse getCounselorDetailAll(String id){
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
}
