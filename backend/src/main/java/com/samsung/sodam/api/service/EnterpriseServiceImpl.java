package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.EnterpriseRequest;
import com.samsung.sodam.db.entity.Enterprise;
import com.samsung.sodam.db.repository.EnterpriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnterpriseServiceImpl implements EnterpriseService{
    private final EnterpriseRepository enterpriseRepository;
    @Override
    public int addEnterprise(EnterpriseRequest request) {
        Enterprise enterprise = enterpriseRepository.save(Enterprise.builder()
                .enterprise(request.getEnterprise())
                .managerId(request.getManagerId())
                .corporateCode(request.getCorporateCode())
                .build());
        return enterprise.getEnterpriseId();
    }

    public List<Enterprise> searchByName(String searchWord, Pageable pageable) {
        return enterpriseRepository.searchByEnterpriseName(searchWord, pageable);
    }

    @Override
    public void existByEnterpriseId(int id) {
        if(!enterpriseRepository.existsByEnterpriseId(id)) throw new IllegalStateException("존재하지 않음");
    }
}
