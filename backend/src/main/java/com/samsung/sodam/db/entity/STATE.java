package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum STATE implements BaseEnumCode<Integer,String> {
    //대기, 승인, 거절, 예정, 진행중
    WAIT("대기", 0),
    APPROVED("승인", 1),
    REJECT("거절", 2),
    EXPECTED("예정", 3),
    PROCEEDING("진행중", 4),
    END("완료", 5);
    final String name;
    final Integer value;
}
