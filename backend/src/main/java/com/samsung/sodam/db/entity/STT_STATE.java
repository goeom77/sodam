package com.samsung.sodam.db.entity;

import com.samsung.sodam.db.converter.BaseEnumCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum STT_STATE implements BaseEnumCode<Integer,String> {
    WAIT("대기", 0),
    PROCEEDING("진행중", 1),
    END("완료", 2),
    STT_ONLY("stt만 사용가능", 3),
    AUDIO_ONLY("오디오만 사용가능", 4),
    ERROR("에러", -1);
    final String name;
    final Integer value;
}
