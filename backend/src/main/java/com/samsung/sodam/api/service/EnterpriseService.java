package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.EnterpriseRequest;
import com.samsung.sodam.db.entity.Enterprise;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EnterpriseService {
    /**
     * 회사 추가
     * @author nuri
     * @param request 회사 이름, 사업자 번호
     * @return 추가된 enterpriseId
     * */
    int addEnterprise(EnterpriseRequest request);

    /**
     * 회사명(enterprise)으로 회사 찾기, 페이지네이션 처리
     * @author nuri
     * @param searchWord 검색어
     * @param pageable page, size
     * @return 회사 리스트
     * */
    List<Enterprise> searchByName(String searchWord, Pageable pageable);

    void existByEnterpriseId(int id);
}
