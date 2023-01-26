package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 세션 생성 이후에 상담신청서 생성됨.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ConsultSession {
    @Id
    @GeneratedValue
    Integer id;
    @Column(name = "client_id")
    String clientId;

    @Column(name = "counselor_id")
    String counselorId;

    STATE status;

}
