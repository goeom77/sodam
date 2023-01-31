package com.samsung.sodam.api.request;

import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.converter.StateAttributeConverter;
import com.samsung.sodam.db.entity.CONSULT_TYPE;
import com.samsung.sodam.db.entity.GENDER;
import com.samsung.sodam.db.entity.STATE;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import java.time.LocalDateTime;

/**
 * 상담요청시 요청할 데이터
 * (최초 상담 요청 시 상담 요청서 작성하는 경우)
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultApplicantRequest {
    private String counselorId;
    private String clientId;
    @Convert(converter = StateAttributeConverter.class)
    private STATE state; // 신청상태
    private String name;
    @Convert(converter = ConsultConverter.class)
    private CONSULT_TYPE consultType;
    private String tel;
    private String email;
    private String content;
    @Convert(converter = GenderConverter.class)
    private GENDER gender;
    private Integer age;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dueDate; // 희망종료날짜
}