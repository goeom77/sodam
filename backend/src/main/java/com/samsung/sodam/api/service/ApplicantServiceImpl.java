package com.samsung.sodam.api.service;

import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.repository.ApplicantCustomRepository;
import com.samsung.sodam.db.repository.ApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    public ApplicantRepository repository;
    public final ApplicantCustomRepository customRepository;

    public ApplicantServiceImpl(ApplicantRepository repository, ApplicantCustomRepository customRepository) {
        this.repository = repository;
        this.customRepository = customRepository;
    }

    @Override
    public List<ConsultApplicant> getMyApplicant(String counselorId) {
        return customRepository.getMyApplicants(counselorId);
    }

    public ConsultApplicant getApplicantById(Integer id){
        return repository.findById(id).orElseThrow();
    }
}
