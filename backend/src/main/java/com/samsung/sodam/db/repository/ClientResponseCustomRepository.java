package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.response.ClientListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientResponseCustomRepository {
//    public Page<ClientListResponse> getClientList(String userId, Pageable pageable);
    public Page<ClientListResponse> getAllClientList(String userId, Pageable pageable);
}
