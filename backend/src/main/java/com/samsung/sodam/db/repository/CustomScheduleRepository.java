package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.Schedule;

interface CustomScheduleRepository {
    public Schedule updateScheduleState(SetStateRequest request);
}
