package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ClientRequest;
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

    final AuthService authService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientResponseCustomRepository clientResponseCustomRepository, AuthService authService) {
        this.clientRepository = clientRepository;
        this.clientResponseCustomRepository = clientResponseCustomRepository;
        this.authService = authService;
    }

    @Override
    public Page<ClientListResponse> getMyClients(String counselorId, Pageable pageable) {
//        if ( name.isEmpty()||name.isBlank()) return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
        return clientResponseCustomRepository.getAllClientList(counselorId, pageable);
    }

    @Override
    public Client getClientDetail(String id){
        Client c = clientRepository.getById(id);
        c.setRoleByCommonCode();
        return c;
    }

    @Override
    public void editProfile(ClientRequest request, String id){
        Client c = clientRepository.getById(id);
        if(request.getTel() != null)  c.setTel(request.getTel());
        if(request.getEmail() != null && !request.getEmail().equals(c.getEmail()))  {
            authService.validateDuplicateEmail(request.getEmail());
            c.setEmail(request.getEmail());
        }
        if(request.getAge() != null) c.setAge(request.getAge());
        clientRepository.save(c);
    }


}
