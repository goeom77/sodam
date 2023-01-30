package com.samsung.sodam.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MEN("M"),
    WOMEN("W");

    private final String gender;

    public static Gender find(String gender) {
        if(gender.equals("M")) return Gender.MEN;
        else if(gender.equals("W")) return Gender.WOMEN;
        else throw new IllegalArgumentException("해당 enum 없음");
    }

}
