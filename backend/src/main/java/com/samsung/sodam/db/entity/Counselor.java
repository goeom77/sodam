package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Counselor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    private String email;
    private String tel;
    private String carrer;
    @Column(name = "refresh_token")
    private String refreshToken;
    @Column(name = "group_code")
    private USER_TYPE groupCode;
    private String major;
    private String gender;
    private STATE qualification;
    private Boolean routine;
    @Column(name = "enterprise_id")
    private Boolean enterpriseId;
}
