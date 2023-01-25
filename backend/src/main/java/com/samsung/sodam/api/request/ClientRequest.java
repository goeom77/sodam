package com.samsung.sodam.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    private String id;
    private String password;
    private String name;

    private String tel;
    private String email;
}