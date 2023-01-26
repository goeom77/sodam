package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.CounselorRepository;
import com.samsung.sodam.jwt.JwtTokenProvider;
import com.samsung.sodam.jwt.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final ClientRepository clientRepository;
    private final CounselorRepository conselorRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private void validateDuplicateMember(String email) {
        boolean existClient = clientRepository.existsByEmail(email);
        boolean existCounselor = conselorRepository.existsByEmail(email);
        if (existClient || existCounselor) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    @Override
    public void validateDuplicateId(String id) {
        boolean existClient = clientRepository.existsById(id);
        boolean existCounselor = conselorRepository.existsById(id);
        if (existClient || existCounselor) {
            throw new IllegalStateException("존재하는 아이디입니다");
        }
    }
    @Override
    public String clientSignup(ClientRequest request) {
        validateDuplicateMember(request.getEmail());

        clientRepository.save(Client.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                .build());

        return request.getId();
    }

    @Override
    public TokenDto login(AuthCommonRequest request) {
        System.out.println("authService - " + request.getId());
        Client client = clientRepository.getById(request.getId());
        Counselor counselor = conselorRepository.getById(request.getId());

        String encodePassword = null;
        int type = -1;

        if(client != null){
            encodePassword = client.getPassword();
            type = 2;
        }else if (counselor != null){
            encodePassword = counselor.getPassword();
            type = 1;
        }else {
            throw new IllegalArgumentException("로그인 실패");
        }
        System.out.println("AuthServiceImpl");
        System.out.println("encodePassword - "+encodePassword);
        System.out.println("input pw - " + request.getPassword());

        if(!passwordEncoder.matches(request.getPassword(), encodePassword)) {
            throw new IllegalArgumentException("로그인 실패");
        }
        return jwtTokenProvider.generateToken(type==2? client.getEmail():counselor.getEmail(), type);
    }



}
