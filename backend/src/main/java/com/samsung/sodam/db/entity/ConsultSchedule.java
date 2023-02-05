package com.samsung.sodam.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultSchedule {
    @Id @GeneratedValue
    private Long id;
    @Column(name = "consult_type")
    private CONSULT_TYPE CONSULTTYPE;
    private String tel;
    private String email;
    @Column(name = "first_day_time")
    private LocalDateTime firstDayTime; //신청일시
    private String content;

    private STATE state; // 신청상태
    @Column(name = "date_time")
    LocalDateTime dateTime;//상담 일시
    @Column(name = "is_consult")
    private String isConsult;
    private Integer turn; // 회차
    @Column(name = "session_id")
    private Integer sessionId;
}