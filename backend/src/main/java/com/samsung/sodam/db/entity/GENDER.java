package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GENDER implements BaseEnumCode<String> {
    MEN("여성","M"),
    WOMEN("남성","W"),
    NONE("알수없음", "N");
    private final String name;
    private final String value;
}
