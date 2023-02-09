package com.samsung.sodam.db.repository.schedule;

import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleCustomRepository {
    Page<ConsultSchedule> getSchedulesByClientId(String counselorId, String clientId);

    Page<ConsultSchedule> getMySchedules(Pageable pageable, String userId);

    List<ConsultApplicant> searchSchedules(SearchSchedule request);


}
