package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthCommonRequest {
    private String id;
    private String password;
    private String email;

    private String confirmCode;
}