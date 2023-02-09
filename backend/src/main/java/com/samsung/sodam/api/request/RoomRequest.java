package com.samsung.sodam.api.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ApiModel(description = "화상상담 세션 요청 모델")
@Getter
@Setter
@ToString
public class RoomRequest {

    private Long scheduleId;
    private String openviduId;

}
