package com.samsung.sodam.api.service.schedule;

import com.samsung.sodam.api.request.SearchSchedule;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.repository.ScheduleRepository;
import com.samsung.sodam.db.repository.schedule.ScheduleCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    final
    ScheduleRepository repository;
    final
    ScheduleCustomRepository customRepository;

    public ScheduleServiceImpl(ScheduleRepository repository, ScheduleCustomRepository customRepository) {
        this.repository = repository;
        this.customRepository = customRepository;
    }

    @Override
    public ConsultSchedule makeSchedule(ConsultSchedule request) {
        return repository.save(request);

    }

    @Override
    public Page<ConsultSchedule> getMySchedules(String userId) {
//        repository.
        return customRepository.getMySchedules(userId);
    }

    @Override
    public List<ConsultApplicant> searchSchedules(SearchSchedule request) {
        return customRepository.searchSchedules(request);
    }
}
