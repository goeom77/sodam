package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.TroubleRequest;
import com.samsung.sodam.api.response.TroubleOneResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface TroubleCustomRepository {

    Page<TroubleOneResponse> getTroubleList(String category, String searchWord, Pageable pageable);
    TroubleOneResponse getTrouble(Long id);
    void updateTrouble(Long id, TroubleRequest request);
    Page<TroubleOneResponse> getMyTroubleList(String clientId, Pageable pageable);

}
