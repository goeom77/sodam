package com.samsung.sodam.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
public class Member {

    private String id;

    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
