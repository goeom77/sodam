package com.samsung.sodam.db.repository.counselor;

import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.Career;
import com.samsung.sodam.db.entity.GENDER;

import java.util.List;

/**
 * A Projection for the {@link com.samsung.sodam.db.entity.Counselor} entity
 */
public interface CounselorInfo {
    String getId();

    String getName();

    String getEmail();

    String getTel();

    List<List<Career>> getCareer();

    String getIntroduce();

    String getCommonCodeId();

    GENDER getGender();

    Boolean isQualification();

    String getRoutine();

    String getProfileImg();

    List<CONSULT_TYPE> getConsultTypeList();

    EnterpriseInfo getEnterprise();
}