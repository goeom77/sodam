package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.RoomRequest;

public interface RoomService {

    void createSession(RoomRequest request);
    void deleteSession(RoomRequest request);
}
