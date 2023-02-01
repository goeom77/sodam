package com.samsung.sodam.api.response;

import com.querydsl.core.annotations.QueryProjection;
import com.samsung.sodam.db.converter.GenderConverter;
import com.samsung.sodam.db.entity.GENDER;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Convert;

@Getter
@Setter
public class CounselorListResponse {
    @ApiModelProperty(value = "상담사 이름")
    private String name;
    @ApiModelProperty(value = "상담사 전화번호")
    private String tel;
    @ApiModelProperty(value = "상담사 이메일")
    private String email;
    @ApiModelProperty(value = "상담사 아이디")
    private String id;
    @ApiModelProperty(value = "상담사 경력")
    private String career;
    @ApiModelProperty(value = "상담사 소개글")
    private String introduce;

    @ApiModelProperty(value = "상담사 전공")
    private String major;
    @ApiModelProperty(value = "상담사 성별")
    @Convert(converter = GenderConverter.class)
    GENDER gender;
    @ApiModelProperty(value = "상담사 이메일")
    private String routine;
    @ApiModelProperty(value = "상담사 프로필 이미지")
    private String profileImg;

    @QueryProjection
    public CounselorListResponse(String name, String tel, String email, String id, String career, String introduce, String major, GENDER gender, String routine, String profileImg) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tel = tel;
        this.career = career;
        this.introduce = introduce;
        this.major = major;
        this.gender = gender;
        this.routine = routine;
        this.profileImg = profileImg;
    }
}
