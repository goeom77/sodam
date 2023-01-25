package com.samsung.sodam.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN","0"),
    COUNSELOR("COUNSELOR","1"),
    CLIENT("CLIENT","2");

    private final String roleName;
    private final String groopCodeId;

}
