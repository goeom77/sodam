package com.samsung.sodam.api.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class AuthCommonResponse {
    private String id;
    private String isFindId;    // "true" / "false"
    private String groupCode;
    private String commonCode;

}
