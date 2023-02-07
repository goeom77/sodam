package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.ConsultConverter;
import com.samsung.sodam.db.converter.ConsultListConverter;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@DynamicInsert
@Builder
public class Counselor extends Member {
    @Id
    @Column(length=30)
    private String id;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String tel;

    @Column(length = 300)
    private String career;

    @Column(length = 300)
    private String education;

    @Column(length = 150)
    private String introduce;

    @Column(length = 200)
    private String refreshToken;

    @Column(length = 3)
    private String commonCodeId;

    @Column(length = 30)
    private String major;

    @Column(length = 3)
    private GENDER gender;
    @Column(columnDefinition = "boolean default false")
    private Boolean qualification;

    @Column(length = 28)
    private String routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(length = 200)
    private String profileImg;

    @Column(name = "consult_type")
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
//    @Convert(converter = ConsultListConverter.class)
    private Set<CONSULT_TYPE> consultTypeList = new HashSet<>();

    @Builder
    public Counselor(String id, String name, String password, String email, String tel, String career, String education, String introduce, String refreshToken, String commonCodeId, String major, GENDER gender, Boolean qualification, String routine, Enterprise enterprise, String profileImg,Set<CONSULT_TYPE> type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.career = career;
        this.education = education;
        this.introduce = introduce;
        this.refreshToken = refreshToken;
        this.major = major;
        this.gender = gender;
        this.qualification = qualification;
        this.routine = routine;
        this.enterprise = enterprise;
        this.profileImg = profileImg;
        this.commonCodeId = "1";
        this.consultTypeList = type;
    }
}
