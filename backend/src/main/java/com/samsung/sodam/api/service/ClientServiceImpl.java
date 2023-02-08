package com.samsung.sodam.api.service;

import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.ClientResponseCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    final ClientRepository clientRepository;
    final ClientResponseCustomRepository clientResponseCustomRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseCustomRepository clientResponseCustomRepository) {
        this.clientRepository = clientRepository;
        this.clientResponseCustomRepository = clientResponseCustomRepository;
    }

    @Override
    public Page<ClientListResponse> getMyClients(String counselorId, Pageable pageable) {
//        if ( name.isEmpty()||name.isBlank()) return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
        return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
    }

    public Client getClientDetail(String id){
        Client c = clientRepository.getById(id);
        c.setRoleByCommonCode();
        return c;
    }
}
