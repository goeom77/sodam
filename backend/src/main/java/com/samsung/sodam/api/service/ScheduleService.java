package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.SetStateRequest;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScheduleService {
    public void setScheduleState(@RequestBody SetStateRequest request);
}
