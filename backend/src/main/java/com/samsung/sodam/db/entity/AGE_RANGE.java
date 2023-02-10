package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AGE_RANGE {
    NONE("미지정", -1),
    UNDER_10("9세 이하", 0), RANGE_10("10대", 1), RANGE_20("20대", 2), RANGE_30("30대", 3),
    RANGE_40("40대", 4), RANGE_50("50대", 5), RANGE_60("60대", 6), OVER_70("70대 이상", 7);

    private final String value;
    Integer code;
}
