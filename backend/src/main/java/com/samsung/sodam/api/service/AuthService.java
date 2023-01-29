package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.db.entity.Counselor;


public interface AuthService {
    /**
     * 고객 회원가입
     * @author nuri
     * @param request id, password, name, tel, email
     * @return
     * */
    String clientSignup(ClientRequest request);

    AuthCommonResponse login(AuthCommonRequest request);

    void validateDuplicateId(String id);

    /**
     * 중복 이메일 확인
     * @author nuri
     * @param email
     * */
    void validateDuplicateEmail(String email);
    
    /**
     * 상담사 회원가입
     *
     * @param request
     * @author nuri
     */
    Counselor counselorSignup(CounselorRequest request);
}
