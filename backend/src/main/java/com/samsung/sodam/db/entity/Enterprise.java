package com.samsung.sodam.db.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Enterprise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int enterpriseId;

    @Column(length = 30)
    private String enterprise;  // 기업명

    @Column(length = 20)
    private String managerId;   // 기업 관리자

    @Column(length = 30)
    private String corporateCode;  // 사업자 번호

    @Builder
    public Enterprise(int enterpriseId, String enterprise, String managerId, String corporateCode) {
        this.enterpriseId = enterpriseId;
        this.enterprise = enterprise;
        this.managerId = managerId;
        this.corporateCode = corporateCode;
    }
}
