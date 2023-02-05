package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.api.response.CounselorListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientResponseCustomRepository {
//    public Page<ClientListResponse> getClientList(String userId, Pageable pageable);
    public Page<ClientListResponse> getAllClientList(String userId, Pageable pageable);
    public List<CounselorListResponse> getReviewByAvg();
}
