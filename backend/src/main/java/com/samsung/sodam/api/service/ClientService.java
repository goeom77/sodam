package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.db.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    Page<ClientListResponse> getMyClients(String counselorId,Pageable pageable);
    public Client getClientDetail(String id);

    void editProfile(ClientRequest request, String id);
}
