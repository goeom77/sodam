package com.samsung.sodam.db.repository.counselor;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.api.response.CounselorDetailResponse;
import com.samsung.sodam.api.response.CounselorListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounselorCustomRepository{
    CounselorListResponse getCounselorDetail(String id);
    public List<CounselorListResponse> getMyFavCounselor(String clientId);
//
//    public Long updateType(TestRequest request, String clientId);
    CounselorDetailResponse getCounselorDetailAll(String userId);

    Page<CounselorListResponse> searchCounselor(CounselorSearchRequest request, Pageable pageable);
}
