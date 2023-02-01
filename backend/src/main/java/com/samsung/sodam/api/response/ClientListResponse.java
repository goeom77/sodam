package com.samsung.sodam.api.response;

import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.entity.GENDER;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientListResponse {
    String id;
    String name;
    String email;
    String tel;
    GENDER gender;

    @QueryProjection
    public ClientListResponse(String name, String tel, String email, String id, String career, String introduce, String major, GENDER gender, String routine, String profileImg) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.gender = gender;
    }
}
