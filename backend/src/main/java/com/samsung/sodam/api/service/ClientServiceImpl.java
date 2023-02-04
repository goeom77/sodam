package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.db.repository.ClientResponseCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    final ClientResponseCustomRepository clientResponseCustomRepository;

    public ClientServiceImpl(ClientResponseCustomRepository clientResponseCustomRepository) {
        this.clientResponseCustomRepository = clientResponseCustomRepository;
    }

    @Override
    public Page<ClientListResponse> getMyClients(String counselorId, Pageable pageable) {
//        if ( name.isEmpty()||name.isBlank()) return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
        return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
    }
}
