package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SearchSchedule;
import com.samsung.sodam.api.service.ApplicantService;
import com.samsung.sodam.api.service.schedule.ScheduleService;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleController {
    final ScheduleService service;
    final ApplicantService applicantService;

    public ScheduleController(ScheduleService service, ApplicantService applicantService) {
        this.service = service;
        this.applicantService = applicantService;
    }

    @PostMapping("/newSchedule")
    @ApiOperation(value = "새로운 일정을 (하나)등록한다")
    public void setApplicantSessionState(@RequestBody ScheduleRequest request) {
        ConsultApplicant a = applicantService.getApplicantById(request.getSessionId());
        ConsultSchedule schedule = ConsultSchedule.builder()
                .content(a.getContent())
                .email(a.getEmail())
                .tel(a.getTel())
                .sessionId(a.getSessionId())
                .CONSULTTYPE(a.getConsultType())
                .firstDayTime(a.getCreatedDateTime())
                .dateTime(request.getDateTime())
                .isConsult("N")
                .turn(0)
                .build();
        service.makeSchedule(schedule);
    }

    @GetMapping("/schedules")
    @ApiOperation(value = "유저id로 본인이 포함된 일정을 조회한다")
    public Page<ConsultSchedule> getSchedules(String userId){
        return service.getMySchedules(userId);
    }


    @PostMapping("/search")
    public List<ConsultApplicant> searchSchedule(@RequestBody SearchSchedule request){
        return service.searchSchedules(request);
    }
}
