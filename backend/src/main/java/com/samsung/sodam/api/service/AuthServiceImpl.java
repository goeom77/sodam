package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.db.entity.*;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.CounselorRepository;
import com.samsung.sodam.db.repository.EnterpriseRepository;
import com.samsung.sodam.jwt.JwtTokenProvider;
import com.samsung.sodam.jwt.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final ClientRepository clientRepository;
    private final CounselorRepository conselorRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void validateDuplicateEmail(String email) {
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
    public Counselor counselorSignup(CounselorRequest request) {
        Enterprise e = enterpriseRepository.getReferenceById(request.getEnterpriseId());
        Counselor c = Counselor.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                .career(request.getCareer())
                .introduce(request.getIntroduce())
                .major(request.getMajor())
                .gender(Gender.find(request.getGender()))
                .routine(Arrays.toString(request.getRoutine()))
                .enterprise(e)
                .build();
        return conselorRepository.save(c);
    }


    @Override
    public String clientSignup(ClientRequest request) {
        validateDuplicateEmail(request.getEmail());

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
    public AuthCommonResponse login(AuthCommonRequest request) {
        AuthCommonResponse response = new AuthCommonResponse(request);

        System.out.println("authService - " + request.getId());
        String encodePassword = null;
        Member member = null;

        System.out.println(request.getCommonCode());
        Role role = Role.find(request.getCommonCode());

        // id로 유저 찾기
        if(role == Role.CLIENT){
            member = clientRepository.getById(request.getId());
        }else if (role == Role.COUNSELOR){
            member = conselorRepository.getById(request.getId());
        }else {
            throw new IllegalArgumentException("로그인 실패");
        }
        encodePassword = member.getPassword();

        // 확인용 코드
        System.out.println("encodePassword - "+encodePassword);
        System.out.println("input pw - " + request.getPassword());
        System.out.println("input encode pw - " + request.getPassword());

        if(!passwordEncoder.matches(request.getPassword(), encodePassword)) {
            throw new IllegalArgumentException("로그인 실패");
        }
        TokenDto token = jwtTokenProvider.generateToken(member.getEmail(), role);
        response.setToken(token);
        response.setName(member.getName());
        return response;
    }



}
