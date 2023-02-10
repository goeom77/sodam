package com.samsung.sodam.api.response;

import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.GENDER;
import lombok.Data;

import javax.persistence.Convert;

@Data
public class ClientResponse {

    private String id;
    private String email;
    private String tel;
    private String name;
    private String commonCodeId = "2";   // 내담자 공통코드
    @Convert(converter = GenderConverter.class)
    private GENDER gender;
    private String age;
    private String profileImg;

    public ClientResponse(Client c) {
        this.id = c.getId();
        this.email = c.getEmail();
        this.tel = c.getTel();
        this.name = c.getName();
        this.commonCodeId = c.getCommonCodeId();
        this.gender = c.getGender();
        this.age = c.getAge().getValue();
        this.profileImg = c.getProfileImg();
    }
}
