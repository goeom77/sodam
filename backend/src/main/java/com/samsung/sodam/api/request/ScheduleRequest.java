package com.samsung.sodam.api.request;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import com.samsung.sodam.db.entity.STATE;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.security.cert.Certificate;
import java.time.LocalDateTime;

/**
 * 상담 세션 생성 요청객체
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {

    @GeneratedValue
    @Id
    private Integer id;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "counselor_id")
    private String counselorId;
    private STATE status;

    private LocalDateTime dateTime;
}
