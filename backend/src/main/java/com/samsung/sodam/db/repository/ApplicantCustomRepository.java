package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultApplicant;
import io.swagger.models.auth.In;

import java.util.List;

public interface ApplicantCustomRepository {
    List<ConsultApplicant> getMyApplicants(String CounselorId) ;
    ConsultApplicant getApplicants(String CounselorId, Integer sessinId) ;
}
