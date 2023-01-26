package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.jwt.TokenDto;


public interface AuthService {

    String clientSignup(ClientRequest request);

    TokenDto login(AuthCommonRequest request);

    void validateDuplicateId(String id);
    void validateDuplicateMember(String email);
}
