package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.converter.StateAttributeConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultApplicant {
    @Id @Column(name = "session_id")
    private Integer sessionId;
    private String name;
    private Integer age;
    @Convert(converter = ConsultConverter.class)
    private CONSULT_TYPE consultType;
    private String tel;
    private String email;
    @Column(name = "create_date_time")
    private LocalDateTime createdDateTime; //신청일시
    private String content;
    @Convert(converter = GenderConverter.class)
    private GENDER gender;
    @Convert(converter = StateAttributeConverter.class)
    private STATE state; // 신청상태
    @Column(name = "is_consult")
    private String isConsult;
    private Integer turn; // 회차

    @Column(name = "due_date")
    private LocalDateTime dueDate;
}