package com.samsung.sodam.db.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

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

    @Column(length = 150)
    private String introduce;

    @Column(length = 200)
    private String refreshToken;

    @Column(length = 3)
    private String groopCodeId;


    @Column(length = 30)
    private String major;

    @Column(length = 3)
    private Gender gender;
    @Column(columnDefinition = "boolean default false")
    private Boolean qualification;

    @Column(length = 28)
    private String routine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(length = 200)
    private String profileImg;


    @Builder
    public Counselor(String id, String name, String password, String email, String tel, String career, String introduce, String refreshToken, String groopCodeId, String major, Gender gender, Boolean qualification, String routine, Enterprise enterprise, String profileImg) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.career = career;
        this.introduce = introduce;
        this.refreshToken = refreshToken;
        this.groopCodeId = groopCodeId;
        this.major = major;
        this.gender = gender;
        this.qualification = qualification;
        this.routine = routine;
        this.enterprise = enterprise;
        this.profileImg = profileImg;

        this.groopCodeId = "1";
    }
}
