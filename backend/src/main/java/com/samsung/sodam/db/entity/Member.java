package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    private String id;

    private String password;

    private String name;

    private String email;
    @Enumerated(EnumType.STRING)
    private Role role;
}
