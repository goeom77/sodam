package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;

public interface ScheduleService {

    ConsultSchedule makeSchedule(ScheduleRequest request);
}
