package com.samsung.sodam.api.service.schedule;

import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ConsultSchedule makeSchedule(ConsultSchedule request);

    Page<ConsultSchedule> getMySchedules(Pageable pageable, String userId);

    List<ConsultApplicant> searchSchedules(SearchSchedule request);
}
