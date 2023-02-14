package com.samsung.sodam.api.service.schedule;

import com.samsung.sodam.api.request.schedule.MonthlyScheduleRequest;
import com.samsung.sodam.api.request.schedule.ScheduleRequest;
import com.samsung.sodam.api.request.schedule.SearchSchedule;
import com.samsung.sodam.api.response.schedule.DetailSchedule;
import com.samsung.sodam.api.response.schedule.MonthlyResponse;
import com.samsung.sodam.api.service.NotificationService;
import com.samsung.sodam.db.entity.ConsultApplicant;
import com.samsung.sodam.db.entity.ConsultSchedule;
import com.samsung.sodam.db.entity.NotificationType;
import com.samsung.sodam.db.repository.ScheduleRepository;
import com.samsung.sodam.db.repository.SessionRepository;
import com.samsung.sodam.db.repository.schedule.ScheduleCustomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    final
    ScheduleRepository repository;
    final
    ScheduleCustomRepository customRepository;
    final
    SessionRepository sessionRepository;
    private final NotificationService notificationService;

    @Override
    public ConsultSchedule makeSchedule(ConsultSchedule request) {
        Integer sessionId = request.getSessionId();
        String clientId = sessionRepository.findById(sessionId).get().getClientId();
        notificationService.send(clientId, NotificationType.CONSULT, "상담신청이 정상적으로 접수되었습니다.",
                "상담사로부터 전화가 갈 예정입니다. 함께 만나기 좋은 날을 맞춰보세요.", "/AlarmView");
        return repository.save(request);
    }

    @Override
    public Page<ConsultSchedule> getMySchedules(Pageable pageable,String userId) {
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
