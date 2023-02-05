package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.SetStateRequest;
import com.samsung.sodam.db.entity.Schedule;
import com.samsung.sodam.db.repository.ScheduleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ScheduleService {
    public void setScheduleState(@RequestBody SetStateRequest request) ;
}
