package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ConsultApplicantRequest;
import com.samsung.sodam.api.request.CounselorSearchRequest;
import com.samsung.sodam.api.request.SessionStateRequest;
import com.samsung.sodam.api.response.ClientListResponse;
import com.samsung.sodam.api.service.ClientService;
import com.samsung.sodam.api.service.CounselorRepositoryService;
import com.samsung.sodam.db.entity.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CounselorController {

    private final CounselorRepositoryService service;
    private final ClientService clientService;

    public CounselorController(CounselorRepositoryService service, ClientService clientService) {
        this.service = service;
        this.clientService = clientService;
    }

    //상담사 검색(목록 보기) 다른 필터링 요소 추가해야함.
//    @PostMapping("counselor/")
//    public Page<Counselor> searchCounselor(CounselorSearchRequest request, @PageableDefault(value = 10) Pageable pageable) {
//        return service.searchCounselor(request, pageable);
//    }

    //상담사 검색(목록 보기) 다른 필터링 요소 추가해야함.
    @PostMapping("counselor/")
    public Page<Counselor> searchCounselor(CounselorSearchRequest request, @PageableDefault(value = 10) Pageable pageable) {
        return service.searchCounselor(request, pageable);
    }

    @ApiOperation(value = "상담사의 상세 정보를 조회")
    @GetMapping("counselor/{id}")
    //상담사 정보 조회
    public Counselor getCounselorInfo(@PathVariable String id) {
        return service.getCounselorInfo(id);
    }

    //상담 예약
    @PostMapping("/consult")
    @ApiOperation(value = "상담 예약")
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
    @ApiOperation(value = "관심상담사 등록")
    public void setFavCounselor(@PathVariable String clientId, @PathVariable String counselorId) {
        FavoriteCounselor fav = new FavoriteCounselor(clientId, counselorId);
        service.setFavCounselor(fav);
    }

    @DeleteMapping("/client/{clientId}/fav/{counselorId}")
    @ApiOperation(value = "관심상담사 삭제")
    public void deleteFavCounselor(@PathVariable String clientId, @PathVariable String counselorId) {
        FavoriteCounselor fav = new FavoriteCounselor(clientId, counselorId);
        service.removeFavCounselor(fav);
    }

    //상담기록(한 세션에 대한 기록)
    @GetMapping("/client/{id}/consult")
    public Page<ConsultSchedule> getHistory(Pageable pageable, @PathVariable Integer id) {
        return service.getHistory(id, pageable);
    }

    //상담세션 만들기
    @PostMapping("/consult-session")
    public Integer makeSession(ConsultSession session) {
        return service.makeSession(session);
    }

    @GetMapping("/client")
    @ApiOperation(value = "모든 고객 목록 조회")
    //고객목록
    public Page<Client> getClients(@PathVariable Pageable pageable) {
        return service.getAllClients(pageable);
    }

    @GetMapping("/client/{consultantId}/{keyword}")
    @ApiOperation(value = "고객 목록 조회")
    //고객목록
    public Page<ClientListResponse> getClients(@PathVariable String consultantId, @PathVariable(required = false) String keyword) {
        return clientService.getMyClients(consultantId,keyword,Pageable.ofSize(20));
    }

    //일정 상세 정보

    //상담 결과지

    //최초 상담 신청
    @PostMapping("/consultApplicant")
    @ApiOperation(value = "상담예약신청")
    public ConsultApplicant makeConsultApplicant(@RequestBody ConsultApplicantRequest request) {
        return service.makeApplicant(request);
    }

}
