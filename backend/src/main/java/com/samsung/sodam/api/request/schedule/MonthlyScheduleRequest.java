package com.samsung.sodam.api.request.schedule;

import com.samsung.sodam.db.entity.STATE;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MonthlyScheduleRequest {

    @ApiModelProperty(value = "상담사 아이디")
    private String counselorId;
//    @ApiModelProperty(value = "검색할 날짜", example = "2023-06-01 14:00:00", required = false)
//    private LocalDateTime dateTime;
}
