package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.api.request.SessionStateRequest;
import com.samsung.sodam.db.entity.*;
import com.samsung.sodam.db.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class CounselorRepositoryService {

    CounselorRepository repository;
    ReviewRepository reviewRepository;
    ScheduleRepository scheduleRepository;
    SessionRepository sessionRepository;
    ApplicantRepository applicantRepository;

    public Page<Counselor> searchCounselor(CounselorSearchRequest request, Pageable pageable) {
        return repository.searchCounselor(request, pageable);
    }

    public Counselor getCounselorInfo(String id) {
        return repository.findByCounselorId(id);
    }

    public List<Review> getReviews(Long id) {
        List<Long> idList = new ArrayList<>();
        idList.add(id);
        return reviewRepository.findAllById(idList);
    }

    public Page<Client> getClients(String id) {
        return null;
    }

    public Page<ConsultSchedule> getHistory(String id, Pageable pageable) {
        return scheduleRepository.getAllSchedule(id, pageable);
    }

    public void setFavCounselor(FavoriteCounselor favoriteCounselor) {

    }

    public Integer makeSession(ConsultSession session) {
        ConsultSession newSession = sessionRepository.save(session);
        return newSession.getId();
    }

    public ConsultSchedule makeNewSchedule(ConsultSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void acceptApplicant(SessionStateRequest request){
        ConsultApplicant applicant = applicantRepository.findById(request.getSessionId()).orElseGet(ConsultApplicant::new);
        applicant.setState(request.getState());
        applicantRepository.save(applicant);
    }

    public ConsultApplicant makeApplicant(ConsultApplicant consultApplicant){
        return applicantRepository.save(consultApplicant);
    }
}
