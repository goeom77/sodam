package com.samsung.sodam.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Enterprise {

    @Id
    @Column(length=30)
    private String enterpriseId;

    @Column(length = 30)
    private String enterprise;  // 기업명

    @Column(length = 20)
    private String managerId;   // 기업 관리자

    @Column(length = 30)
    private String corporateCode;  // 사업자 번호



}
