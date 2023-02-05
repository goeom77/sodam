package com.samsung.sodam.db.repository;

import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.Schedule;
import org.springframework.stereotype.Service;

@Service
public class CustomScheduleRepositoryImpl implements CustomScheduleRepository {

    public Schedule updateScheduleState(SetStateRequest request){
        return null;
    }
}
