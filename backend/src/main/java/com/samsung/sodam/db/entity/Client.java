package com.samsung.sodam.db.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Client extends Member {

    @Id
    @Column(length=30)
    private String id;


    @Column(length = 100)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(length = 50)
    private String tel;

    @Column(length = 50)
    private String name;

    @Column(length = 200)
    private String refreshToken;

    @Column(length = 3)
    private String commonCodeId = "2";   // 내담자 공통코드

    @Column(length = 200)
    private String profileImg;

    @Builder
    public Client(String id, String password, String email, String tel, String name, String profileImg) {
        super();
        this.id = id;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.name = name;
        this.profileImg = profileImg;
    }
}
