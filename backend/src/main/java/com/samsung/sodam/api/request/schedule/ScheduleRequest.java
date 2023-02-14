package com.samsung.sodam.api.request.schedule;

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
    @ApiModelProperty(required = true)
    private Integer sessionId;
//    @Column(name = "client_id")
//    @ApiModelProperty(required = false)
//    private String clientId;
//    @Column(name = "counselor_id")
//    @ApiModelProperty(required = false)
//    private String counselorId;
    private STATE status=STATE.WAIT;

    @ApiModelProperty(value = "상담 일정", example = "2023-02-14T00:44:02", required = true)
    private LocalDateTime dateTime = LocalDateTime.now();
}
