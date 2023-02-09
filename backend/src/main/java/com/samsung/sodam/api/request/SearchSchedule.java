package com.samsung.sodam.api.request;

import com.samsung.sodam.db.converter.StateAttributeConverter;
import com.samsung.sodam.db.entity.STATE;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Convert;
import java.time.LocalDateTime;

@Data
public class SearchSchedule {
    @Convert(converter = StateAttributeConverter.class)
    public STATE state = STATE.WAIT;
    public String userId; //상담사 아이디

    public LocalDateTime start = LocalDateTime.now();
    public LocalDateTime end = LocalDateTime.now().plusYears(1);
}
