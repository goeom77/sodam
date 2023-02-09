package com.samsung.sodam.api.response.schedule;

import com.samsung.sodam.db.converter.StateAttributeConverter;
import com.samsung.sodam.db.entity.STATE;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Convert;
import java.time.LocalDateTime;

//월별 캘린더에 뿌려줄 데이터 담는 객체
@Data
@Builder
public class MonthlyResponse {
    String scheduleId;
    String title;
    LocalDateTime start;
    LocalDateTime end;
    Integer sessionId;
    @Convert(converter = StateAttributeConverter.class)
    STATE state = STATE.WAIT;
}
