package com.samsung.sodam.api.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.samsung.sodam.db.entity.GENDER;
import com.samsung.sodam.db.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class CounselorRequest {

    private String isKakaoRegister = "false";
    private String id;
    private String password;
    private String name;

    private String tel;
    private String email;

    private String career;

    private String introduce;
    private String major;
    private GENDER gender;
    private String[] routine;
    private Role role = Role.COUNSELOR;

    private int enterpriseId;

    private String profileImg;
}