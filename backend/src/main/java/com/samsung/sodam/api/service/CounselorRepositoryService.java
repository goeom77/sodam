package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.*;
import com.samsung.sodam.api.response.CounselorListResponse;
import com.samsung.sodam.db.entity.*;
import com.samsung.sodam.db.repository.*;
import com.samsung.sodam.db.repository.counselor.CounselorCustomRepository;
import com.samsung.sodam.db.repository.counselor.CounselorRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Setter
@Getter
public class CounselorRepositoryService {

    @Autowired
    SessionRepository sessionRepository;
    CounselorRepository repository;

    CounselorCustomRepository counselorCustomRepository;
    ScheduleRepository scheduleRepository;

    ApplicantCustomRepository applicantCustomRepository;
    ApplicantRepository applicantRepository;
    FavoriteRepository favoriteRepository;
    ClientRepository clientRepository;

    final AuthService authService;

//    public Page<Counselor> searchCounselor(CounselorSearchRequest request, Pageable pageable) {
//        return repository.find;
//    }

    public Page<Counselor> getAllCounselor( Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Counselor getCounselorInfo(String id) {
        return repository.getById(id);
    }
    public Long counselorTest(TestRequest request, String id) {
//        System.out.println("service :: "+request.getList().toString());
//        System.out.println("service :: "+request.getList().getClass());
//        return counselorCustomRepository.updateType(request,id);
        return null;
    }


    // 파일 제외 수정임
    public void editProfile(CounselorRequest request, String id){

        Counselor counselor = repository.getById(id);
        // 전화번호 수정
        if(request.getTel() != null) counselor.setTel(request.getTel());

        if(request.getName() != null) counselor.setName(request.getName());

        // 이메일 수정
        if(request.getEmail() != null && !counselor.getEmail().equals(request.getEmail())) {
            authService.validateDuplicateEmail(request.getEmail());
            counselor.setEmail(request.getEmail());
        }

        // 소개 수정
        if(request.getIntroduce()!= null) {
            counselor.setIntroduce(request.getIntroduce());
        }
        // 경력 수정
        if(request.getCareers() != null){
            counselor.setCareer(request.getCareers());
        }

        if(request.getConsultType() != null &&!request.getConsultType().isEmpty()){
            counselor.setConsultTypeList(request.getConsultType());
        }
        System.out.println(counselor.getConsultTypeList().toString());

        repository.save(counselor);
    }

    public CounselorListResponse getCounselorDetail(String id) {
        return counselorCustomRepository.getCounselorDetail(id);
    }

//    public List<Review> getReviews(String id) {
//        return reviewRepository.findAllByClientId(id);
//    }

    public Page<CounselorListResponse> getBestCounleor(Pageable pageable){
        return counselorCustomRepository.getBestCounselor(pageable);
    }

    public Page<Client> getAllClients(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

//    public Page<Client> getMyClients(Pageable pageable,String id) {
//        Specification
//        return clientRepository.findAll();
//    }

    public Page<ConsultSchedule> getHistory(Integer id, Pageable pageable) {
        return scheduleRepository.getAllBySessionId(id, pageable);
    }

    public void setFavCounselor(FavoriteCounselor favoriteCounselor) {
        favoriteRepository.save(favoriteCounselor);
    }

    public List<CounselorListResponse> getMyFavCounselor(String clientId) {
        return counselorCustomRepository.getMyFavCounselor(clientId);
    }


    public void removeFavCounselor(FavoriteCounselor favoriteCounselor) {
        favoriteRepository.delete(favoriteCounselor);
    }

    public Integer makeSession(ConsultSession session) {
        ConsultSession newSession = sessionRepository.save(session);
        return newSession.getId();
    }

    public List<ConsultSession> getMySession(String counselorId) {
        return sessionRepository.findByCounselorId(counselorId);
    }

    public ConsultSchedule makeNewSchedule(ConsultSchedule schedule) {
        return scheduleRepository.save(schedule);
    }

    public void acceptApplicant(SessionStateRequest request) {
        ConsultApplicant applicant = applicantRepository.findById(request.getSessionId()).orElseGet(ConsultApplicant::new);
        applicant.setState(request.getState());
        applicantRepository.save(applicant);
    }

    /**
     * @param request
     * @return applicant : 생성된 상담신청서
     * 상담신청을 하면 상담세션과 상담신청서가 생성된다.
     * 상담세션과 상담신청서 id는 동일
     */
    @Transactional
    public ConsultApplicant makeApplicant(ConsultApplicantRequest request) {
        ConsultSession session = ConsultSession.builder()
                .counselorId(request.getCounselorId())
                .clientId(request.getClientId())
                .status(STATE.WAIT)
                .build();

        ConsultSession newSession = sessionRepository.save(session);

        ConsultApplicant applicant = ConsultApplicant.builder()
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                .age(request.getAge())
                .content(request.getContent())
                .dueDate(request.getDueDate())
                .gender(request.getGender())
                .createdDateTime(LocalDateTime.now())
                .turn(0)
                .state(request.getState())
                .isConsult("N")
                .sessionId(newSession.getId())
                .consultType(request.getConsultType())
                .build();

        return applicantRepository.save(applicant);
    }

    /**
     * @param request
     * @return applicant : 수락/거절할 상담요청
     * 상담신청을 하면 상담세션과 상담신청서가 생성된다.
     */
    @Transactional
    public void setApplicationState(SetStateRequest request,String consultantId) {
        ConsultSession session = sessionRepository.getReferenceById(request.getSessionId());
        if(session==null) return;
        session.setStatus(request.getState());
        sessionRepository.flush();

        ConsultApplicant applicant = applicantCustomRepository.getApplicants(consultantId,request.getSessionId());
        if(applicant==null) return;
//        applicantRepository.getReferenceById(request.getSessionId());
        applicant.setState(request.getState());
        applicantRepository.flush();

    }


    @Transactional
    public Page<CounselorListResponse> searchCounselor(CounselorSearchRequest request,Pageable pageable){
        return counselorCustomRepository.searchCounselor(request,pageable);
    }


}
