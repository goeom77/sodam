package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum STATE {
    //대기, 승인, 거절, 예정, 진행중
    WAIT(0),
    APPROVED(1),
    REJECT(2),
    EXPECTED(3) ,
    PROCEEDING(4),
    END(5);

    Integer value;

    public static STATE fromCode(Integer dbData){
        return Arrays.stream(STATE.values()).filter(v-> v.getValue().equals(dbData))
                .findAny().orElseThrow(()-> new IllegalArgumentException(""));
    }
}
