package com.samsung.sodam.api.response;

import com.samsung.sodam.db.entity.Enterprise;
import lombok.Data;

import java.util.List;

@Data
public class EnterpriseResponse {
    private List<Enterprise> list;

}
