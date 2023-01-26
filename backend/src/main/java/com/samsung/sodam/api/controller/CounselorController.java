package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.api.request.SessionStateRequest;
import com.samsung.sodam.api.service.CounselorRepositoryService;
import com.samsung.sodam.db.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CounselorController {

    private final CounselorRepositoryService service;

    public CounselorController(CounselorRepositoryService service) {
        this.service = service;
    }

    //상담사 검색(목록 보기) 다른 필터링 요소 추가해야함.
//    @PostMapping("counselor/")
//    public Page<Counselor> searchCounselor(CounselorSearchRequest request, @PageableDefault(value = 10) Pageable pageable) {
//        return service.searchCounselor(request, pageable);
//    }

    @GetMapping("counselor/{id}")
    //상담사 정보 조회
    public Counselor getCounselorInfo(@PathVariable String id) {
        return service.getCounselorInfo(id);
    }

    //상담 예약
    @PostMapping("/consult")
    public void makeNewSchedule(ConsultSchedule schedule) {
        service.makeNewSchedule(schedule);
    }

    //상담 예약 확정
    @PostMapping("/consult/{consult_id}")
    public void acceptApplicant(@PathVariable String consult_id, @RequestBody SessionStateRequest request) {
        service.acceptApplicant(request);
    }

    //상담사 후기 목록 보기
//    public List<Review> getReviews(Long id) {
//        return service.getReviews(id);
//    }

    //관심 상담사 담기
    @PostMapping("/client/{clientId}/fav/{counselorId}")
    public void setFavCounselor(@PathVariable String clientId, @PathVariable String counselorId) {
        FavoriteCounselor fav = new FavoriteCounselor(clientId, counselorId);
        service.setFavCounselor(fav);
    }

    @DeleteMapping("/client/{clientId}/fav/{counselorId}")
    public void deleteFavCounselor(@PathVariable String clientId, @PathVariable String counselorId) {
        FavoriteCounselor fav = new FavoriteCounselor(clientId, counselorId);
        service.removeFavCounselor(fav);
    }

    //상담기록
    @GetMapping("/client/{id}/consult")
    public Page<ConsultSchedule> getHistory(Pageable pageable, @PathVariable String id) {
        return service.getHistory(id, pageable);
    }

    //상담세션 만들기
    @PostMapping("/consult-session")
    public Integer makeSession(ConsultSession session) {
        return service.makeSession(session);
    }

    @GetMapping("/client/{consultantId}")
    //고객목록
    public Page<Client> getClients(@PathVariable String consultantId) {
        return service.getClients(consultantId);
    }

    //일정 상세 정보

    //상담 결과지

}
