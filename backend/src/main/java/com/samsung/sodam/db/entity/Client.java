package com.samsung.sodam.db.entity;

import com.samsung.sodam.jwt.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
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
    private String groopCodeId = "2";   // 내담자 그룹코드

    @Builder
    public Client(String id, String password, String email, String tel, String name) {
        super();
        this.id = id;
        this.password = password;
        this.email = email;
        this.tel = tel;
        this.name = name;
    }
}
