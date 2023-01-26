package com.samsung.sodam.api.request;

import com.samsung.sodam.jwt.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounselorRequest {
    private String id;
    private String password;
    private String name;

    private String tel;
    private String email;

    private String career;

    private String introduce;
    private String major;
    private String gender;
    private String routine;
    private Role role = Role.COUNSELOR;


}