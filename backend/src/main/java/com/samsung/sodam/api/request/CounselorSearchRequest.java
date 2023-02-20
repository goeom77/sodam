package com.samsung.sodam.api.request;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.GENDER;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Convert;
import java.security.cert.Certificate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CounselorSearchRequest {
    // keyword 는 사람이름, 상담대상, 13개 카테고리
    private String keyword;
    @Convert(converter = GenderConverter.class)
    private GENDER gender;
    @Convert(converter = ConsultConverter.class)
    private CONSULT_TYPE type = CONSULT_TYPE.ALL;
}
