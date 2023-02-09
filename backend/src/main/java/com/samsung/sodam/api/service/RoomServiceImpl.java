package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.db.repository.ScheduleRepository;
import com.samsung.sodam.db.repository.schedule.ScheduleCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final ScheduleRepository scheduleRepository;
    private final ScheduleCustomRepository scheduleCustomRepository;

    @Override
    public void createSession(RoomRequest request) {
        scheduleCustomRepository.createSession(request);
    }

    @Override
    public void deleteSession(RoomRequest request) {
        scheduleCustomRepository.deleteSession(request);
    }
}
