package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CATEGORY {
    CHILD(0, "child"),
    TEENAGER(1, "teenager"),
    ADULT(2, "adult"),
    COUPLE(3, "couple"),
    ELDER(4, "elder"),
    OTHER(5, "other");

    private final Integer code;
    private final String value;
}
