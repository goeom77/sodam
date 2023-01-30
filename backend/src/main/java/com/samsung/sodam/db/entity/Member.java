package com.samsung.sodam.db.entity;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Member {

    @Id
    private String id;

    private String password;

    private String name;

    private String email;

    private String commonCodeId;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void setRoleByCommonCode() {
        this.role = Role.find(this.getCommonCodeId());
    }
}
