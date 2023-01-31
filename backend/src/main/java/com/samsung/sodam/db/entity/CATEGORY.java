package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CATEGORY implements BaseEnumCode<Integer> {
    CHILD(0, "child"),
    TEENAGER(1, "teenager"),
    ADULT(2, "adult"),
    COUPLE(3, "couple"),
    ELDER(4, "elder"),
    OTHER(5, "other");

    private final Integer value;
    private final String name;
}
