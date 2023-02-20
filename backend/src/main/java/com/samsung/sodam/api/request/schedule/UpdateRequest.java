package com.samsung.sodam.api.request.schedule;

import com.samsung.sodam.db.entity.STATE;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    @Column(name = "session_id")
    @ApiModelProperty(required = true)
    private Integer sessionId;

    private Long scheduleId;

    private LocalDateTime start = LocalDateTime.now();

    STATE state = STATE.EXPECTED;
}
