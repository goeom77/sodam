package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CATEGORY {
    CHILD("0", "아동"),
    TEENAGER("1", "청소년"),
    ADULT("2", "성인"),
    COUPLE("3", "가족"),
    ELDER("4", "노년"),
    OTHER("5", "기타");

    private final String code;
    private final String value;
}
