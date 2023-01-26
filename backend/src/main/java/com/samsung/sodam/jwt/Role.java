package com.samsung.sodam.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum Role {
    ADMIN("ADMIN","0"),
    COUNSELOR("COUNSELOR","1"),
    CLIENT("CLIENT","2");

    private final String roleName;
    private final String groopCodeId;


    private static final Map<String, Role> OPERATOR_MAP =
            Collections.unmodifiableMap(Stream.of(values())
                    .collect(Collectors.toMap(Role::getGroopCodeId, Function.identity())));

    public static Role find(String groopCodeId) {
        if (OPERATOR_MAP.containsKey(groopCodeId)) {
            return OPERATOR_MAP.get(groopCodeId);
        }
        throw new IllegalArgumentException("연산자를 찾을 수 없습니다.");
    }
}
