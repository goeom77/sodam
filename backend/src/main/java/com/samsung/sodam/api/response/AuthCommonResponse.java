package com.samsung.sodam.api.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.jwt.TokenDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
    public class AuthCommonResponse {
        private String id;
        private String name;
        private String isFindId = "false";    // "true" / "false"
        private String isFirst = "false";    // "true" / "false"
        private String isInfoRequired = "false";
        private String groupCode;
        private String commonCode;

        private TokenDto token;


    public AuthCommonResponse(AuthCommonRequest request) {
        if(request.getIsFindId() != null) this.isFindId = request.getIsFindId();
        this.id = request.getId();
        this.groupCode = request.getGroupCode();
        this.commonCode = request.getCommonCode();
    }
}
