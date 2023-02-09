package com.samsung.sodam.api.request.schedule;

import com.samsung.sodam.db.converter.StateAttributeConverter;
import com.samsung.sodam.db.entity.STATE;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;

import javax.persistence.Convert;
import java.time.LocalDateTime;

@Data
public class SearchSchedule {
    @Convert(converter = StateAttributeConverter.class)
    public STATE state = STATE.WAIT;
    public String userId; //상담사 아이디

    @ApiModelProperty(hidden = true)
    public LocalDateTime start = LocalDateTime.now();
    @ApiModelProperty(hidden = true)
    public LocalDateTime end = LocalDateTime.now().plusYears(1);
}
