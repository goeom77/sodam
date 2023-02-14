package com.samsung.sodam.api.service.schedule;

import com.samsung.sodam.api.request.schedule.MonthlyScheduleRequest;
import com.samsung.sodam.api.request.schedule.ScheduleRequest;
import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.api.response.schedule.DetailSchedule;
import com.samsung.sodam.api.response.schedule.MonthlyResponse;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.repository.ScheduleRepository;
import com.samsung.sodam.db.repository.schedule.ScheduleCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<ConsultSchedule> getMySchedules(Pageable pageable,String userId) {
//        repository.
        return customRepository.getMySchedules(pageable,userId);
    }

    @Override
    public List<ConsultApplicant> searchSchedules(SearchSchedule request) {
        return customRepository.searchSchedules(request);
    }

    @Override
    public List<MonthlyResponse> getMonthlySchedule(MonthlyScheduleRequest request) {
        return customRepository.getMonthlySchedule(request).stream().map(it->{
            it.setEnd(it.getStart().plusMinutes(50));
            return it;
        }).collect(Collectors.toList());
    }

    @Override
    public DetailSchedule scheduleDetail(ScheduleRequest request) {
        return customRepository.getDetail(request);
    }
}
