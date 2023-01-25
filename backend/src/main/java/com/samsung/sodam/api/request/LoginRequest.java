package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String id;
    private String password;
}