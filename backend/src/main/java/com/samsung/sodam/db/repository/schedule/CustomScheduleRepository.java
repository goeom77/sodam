package com.samsung.sodam.db.repository.schedule;

import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;

interface CustomScheduleRepository {
    public ConsultSchedule updateScheduleState(SetStateRequest request);
}
