package com.samsung.sodam.api.request;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.cert.Certificate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CounselorSearchRequest {
    // keyword 는 사람이름, 상담대상, 13개 카테고리
    private String keyword;
    private Gender gender;
    private Certificate certificate;
    private String price;
}
