package com.samsung.sodam.db.repository;

import com.samsung.sodam.db.entity.ConsultApplicant;

import java.util.List;

public interface ApplicantCustomRepository {
    List<ConsultApplicant> getMyApplicants(String CounselorId) ;
}
