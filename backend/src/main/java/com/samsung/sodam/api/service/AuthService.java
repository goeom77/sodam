package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.LoginRequest;
import com.samsung.sodam.jwt.TokenDto;


public interface AuthService {

    String clientSignup(ClientRequest request);

    TokenDto login(LoginRequest request);

    void validateDuplicateId(String id);
}
