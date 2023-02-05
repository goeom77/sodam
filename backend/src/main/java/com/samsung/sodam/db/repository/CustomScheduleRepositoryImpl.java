package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {

    public ConsultSchedule updateScheduleState(SetStateRequest request) {
        return null;
    }
}
