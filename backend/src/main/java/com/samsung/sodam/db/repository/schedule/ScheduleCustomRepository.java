package com.samsung.sodam.db.repository.schedule;

import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;

public interface ScheduleCustomRepository {
    Page<ConsultSchedule> getSchedulesByClientId(String counselorId, String clientId);

    Page<ConsultSchedule> getMySchedules(String userId);

}
