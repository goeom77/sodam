package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.StateAttributeConverter;
import lombok.*;

import javax.persistence.*;

/**
 * 세션 생성 이후에 상담신청서 생성됨.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ConsultSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @Column(name = "client_id")
    String clientId;

    @Column(name = "counselor_id")
    String counselorId;

    @Convert(converter = StateAttributeConverter.class)
    STATE status;

    @Column(name = "openvidu_id")
    private String openviduId; // 화상상담 세션 ID

}
