package com.samsung.sodam.api.request;

import lombok.Data;

@Data
public class EnterpriseRequest {
    private String enterprise;
    private String managerId;
    private String corporateCode; // 사업자 번호

}
