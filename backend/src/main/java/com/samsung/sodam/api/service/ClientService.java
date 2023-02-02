package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.ClientListResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientListResponse> getMyClients(String counselorId, String name, Pageable pageable);
}
