package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.ScheduleRequest;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.repository.ScheduleRepository;

public class ScheduleServiceImpl implements ScheduleService{

    ScheduleRepository repository;
    @Override
    public ConsultSchedule makeSchedule(ScheduleRequest request) {
//        return repository.save();
        return null;
    }
}
