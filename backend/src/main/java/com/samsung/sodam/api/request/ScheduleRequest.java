package com.samsung.sodam.api.request;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import com.samsung.sodam.db.entity.STATE;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    @ApiModelProperty(hidden = true)
    private Integer id;

    @Column(name = "session_id")
    private Integer sessionId;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "counselor_id")
    private String counselorId;
    private STATE status=STATE.WAIT;

    @ApiModelProperty(value = "상담 일정", example = "2023-06-01 14:00:00", required = true)
    private LocalDateTime dateTime;
}
