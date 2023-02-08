package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;

interface CustomScheduleRepository {
    public ConsultSchedule makeSchedule(ScheduleRequest request);
    public ConsultSchedule updateScheduleState(SetStateRequest request);
}
