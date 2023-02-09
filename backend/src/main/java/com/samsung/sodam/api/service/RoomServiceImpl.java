package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.db.repository.ScheduleRepository;
import com.samsung.sodam.db.repository.SessionCustomRepository;
import com.samsung.sodam.db.repository.schedule.ScheduleCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService{

    private final SessionCustomRepository sessionCustomRepository;

    @Override
    public void createSession(RoomRequest request) {
        sessionCustomRepository.createSession(request);
    }

    @Override
    public void deleteSession(RoomRequest request) {
        sessionCustomRepository.deleteSession(request);
    }
}
