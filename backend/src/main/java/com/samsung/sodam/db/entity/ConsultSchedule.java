package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.SttStateAttributeConverter;
import lombok.*;

import javax.persistence.*;
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
    private CONSULT_TYPE CONSULTTYPE = CONSULT_TYPE.ALL;
    private String tel;
    private String email;
    @Column(name = "first_day_time")
    private LocalDateTime firstDayTime; //신청일시
    private String content;

    private STATE state =STATE.WAIT; // 신청상태
    @Column(name = "date_time")
    LocalDateTime dateTime;//상담 일시
    @Column(name = "is_consult")
    private String isConsult;
    private Integer turn; // 회차
    @Column(name = "session_id")
    private Integer sessionId;

    @Column(name = "consult_result")
    private String consultResult;

    @Convert(converter = SttStateAttributeConverter.class)
    STT_STATE stt_status;
}