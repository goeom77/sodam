package com.samsung.sodam.api.response.schedule;

import com.samsung.sodam.db.converter.StateAttributeConverter;
import com.samsung.sodam.db.entity.STATE;
import lombok.*;

import javax.persistence.Convert;
import java.time.LocalDateTime;

//월별 캘린더에 뿌려줄 데이터 담는 객체
@Data
@Builder
@Setter
@Getter
@AllArgsConstructor
public class MonthlyResponse {
    Long scheduleId;
    String title;
    LocalDateTime start;
    LocalDateTime end;
    Integer sessionId;
    @Convert(converter = StateAttributeConverter.class)
    STATE state = STATE.WAIT;
}
