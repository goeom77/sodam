package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.api.response.ResponseDto;

public interface RoomService {

    void createSession(RoomRequest request);
    void deleteSession(RoomRequest request);
    ResponseDto<?> startRecord(String sessionId);
    ResponseDto<?> stopRecord(String sessionId, Integer scheduleId);
}
