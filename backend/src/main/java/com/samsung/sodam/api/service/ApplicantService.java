package com.samsung.sodam.api.service;

import com.samsung.sodam.db.entity.ConsultApplicant;

import java.util.List;

public interface ApplicantService {

    public List<ConsultApplicant> getMyApplicant(String counselorId);
    public ConsultApplicant getApplicantById(Integer id);
}
