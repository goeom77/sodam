package com.samsung.sodam.api.response;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.jwt.TokenDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthKakaoResponse {

    private String isFirst = "false";    // "true" / "false"
    private String isInfoRequired = "false";
    private String id;
    private String email;
    private String name;

    private String groupCode;
    private String commonCode;
    TokenDto token;
    public AuthKakaoResponse(AuthCommonRequest request) {
        this.groupCode = request.getGroupCode();
        this.commonCode = request.getCommonCode();
    }

}
