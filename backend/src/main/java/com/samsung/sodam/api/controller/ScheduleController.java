package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.service.ScheduleService;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ScheduleController {

    ScheduleService service;
    @PostMapping("consult/schedule")
    ConsultSchedule makeSchedule(@RequestBody ScheduleRequest request){
        return service.makeSchedule(request);
    }
}
