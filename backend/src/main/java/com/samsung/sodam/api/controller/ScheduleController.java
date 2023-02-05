package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.api.service.ScheduleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleController {


    ScheduleService service;
    @PostMapping("/newSchedule")
    @ApiOperation(value = "새로운 일정을 (하나)등록한다")
    public void setApplicantSessionState(@RequestBody ScheduleRequest request) {
        service.makeSchedule(request);
    }

}
