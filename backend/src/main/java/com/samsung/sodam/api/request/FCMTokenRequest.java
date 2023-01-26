package com.samsung.sodam.api.request;

import lombok.Getter;

@Getter
public class FCMTokenRequest {

    private String id;
    private String token;
    private String lastTime;
}
