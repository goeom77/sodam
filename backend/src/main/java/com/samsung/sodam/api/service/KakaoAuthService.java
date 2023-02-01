package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.jwt.KakaoUser;
import com.samsung.sodam.jwt.TokenDto;

public interface KakaoAuthService {
    public TokenDto getToken(String code);
    public KakaoUser getUserInfo(String accessToken);

    Client clientSignupKakao(KakaoUser user);

    AuthCommonResponse kakaoLogin(AuthCommonRequest request);

    public Counselor counselorSignupKakao(CounselorRequest request);
}
