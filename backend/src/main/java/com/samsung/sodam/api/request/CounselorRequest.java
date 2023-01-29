package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
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
    private String[] routine;
    private Role role = Role.COUNSELOR;

    private int enterpriseId;


}