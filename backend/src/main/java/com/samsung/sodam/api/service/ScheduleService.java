package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScheduleService {
    ConsultSchedule makeSchedule(ScheduleRequest request);
}
