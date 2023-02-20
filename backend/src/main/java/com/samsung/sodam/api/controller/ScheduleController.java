package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.schedule.MonthlyScheduleRequest;
import com.samsung.sodam.api.request.schedule.ScheduleRequest;
import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.api.request.schedule.UpdateRequest;
import com.samsung.sodam.api.response.schedule.DetailSchedule;
import com.samsung.sodam.api.response.schedule.MonthlyResponse;
import com.samsung.sodam.api.service.ApplicantService;
import com.samsung.sodam.api.service.schedule.ScheduleService;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.entity.STATE;
import com.samsung.sodam.db.repository.ScheduleRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleController {
    final ScheduleService service;
    final ApplicantService applicantService;
    private final ScheduleRepository scheduleRepository;

    public ScheduleController(ScheduleService service, ApplicantService applicantService,
                              ScheduleRepository scheduleRepository) {
        this.service = service;
        this.applicantService = applicantService;
        this.scheduleRepository = scheduleRepository;
    }

    @PostMapping("/newSchedule")
    @ApiOperation(value = "새로운 일정을 (하나)등록한다")
    public ConsultSchedule setApplicantSessionState(@RequestBody ScheduleRequest request) {
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

        return service.makeSchedule(schedule);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "일정 상세화면을 조회한다")
    public DetailSchedule detailSchedule(@RequestBody ScheduleRequest request) {
        return service.scheduleDetail(request);
    }

    @GetMapping("/schedules")
    @ApiOperation(value = "유저id로 본인이 포함된 일정을 조회한다")
    public Page<ConsultSchedule> getSchedules(Pageable pageable, @Validated String userId) {
        if (pageable == null) pageable = Pageable.ofSize(20);
        return service.getMySchedules(pageable, userId);
    }

    @GetMapping("/schedules/detail")
    @ApiOperation(value = "sessionid로 본인이 포함된 일정을 조회한다")
    public Page<ConsultSchedule> getSchedules(Pageable pageable, @Validated Integer sessionId) {
        if (pageable == null) pageable = Pageable.ofSize(20);
        return service.getMySchedules(pageable, sessionId);
    }


    @PostMapping("/search")
    @ApiOperation(value = "입력한 기간 내의 승인된 상태의 상담신청정보를 조회한다", notes = "기간 미입력 시 오늘기준 1년 이후까지의 목록 보여줌.")
    public List<ConsultApplicant> searchSchedule(@Validated @RequestBody SearchSchedule request) {
        return service.searchSchedules(request);
    }

    @PostMapping("/search/monthly")
    @ApiOperation(value = "설정한 기간의 상담일정을 조회한다", notes = "월별,년별등 대량 데이터 조회 시 이용할 데이터 일부만 내려줌.")
    public List<MonthlyResponse> getMonthlySchedule(@RequestBody MonthlyScheduleRequest request) {
        return service.getMonthlySchedule(request);
    }

    @Transactional
    @PostMapping("/update/monthly")
    @ApiOperation(value = "일정을 수정한다.", notes = "캘린더에 있던 일정을 다른 날짜로 옮기거나 상세 내용 수정할 때.")
    public MonthlyResponse updateMonthlySchedule(@RequestBody UpdateRequest request) {
        ConsultSchedule result;
        if (request.getScheduleId() == null) {
            ConsultApplicant a = applicantService.getApplicantById(request.getSessionId());
            ConsultSchedule schedule = ConsultSchedule.builder()
                    .content(a.getContent())
                    .email(a.getEmail())
                    .tel(a.getTel())
                    .sessionId(a.getSessionId())
                    .CONSULTTYPE(a.getConsultType())
                    .firstDayTime(a.getCreatedDateTime())
                    .dateTime(request.getStart())
                    .isConsult("N")
                    .turn(0)
                    .build();
            result = service.makeSchedule(schedule);
//        }
//            setApplicantSessionState(ScheduleRequest.builder()
//                    .status(STATE.EXPECTED)
//                    .dateTime(request.getDateTime())
//                    .sessionId(request.getSessionId())
//                    .build();
//
        } else {
            result = setApplicantSessionState(ScheduleRequest.builder()
                    .status(STATE.EXPECTED)
                    .dateTime(request.getStart())
                    .sessionId(request.getSessionId())
                    .build());
//            result = service.updateScheduleTime(request);
        }
        return new MonthlyResponse(request.getScheduleId(), "유저이름", request.getStart(), request.getStart().plusMinutes(50));
    }

    @Transactional
    @PostMapping("/update/state")
    @ApiOperation(value = "일정진행 상태를 수정한다.", notes = "상담일정을 수정하거나 화상상담을 입장했을 때.")
    public void updateScheduleState(@RequestBody UpdateRequest request) {
        Pageable pageable = Pageable.ofSize(20);
        Optional<ConsultSchedule> cs= scheduleRepository.getConsultSchedulesBySessionId(request.getSessionId());
        if(cs.isPresent()){
            ConsultSchedule r = cs.get();
            scheduleRepository.save(r);
        }
    }

}