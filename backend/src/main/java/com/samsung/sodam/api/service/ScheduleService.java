package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ScheduleService {
    ConsultSchedule makeSchedule(ConsultSchedule request);

    Page<ConsultSchedule> getMySchedules(String userId);
}
