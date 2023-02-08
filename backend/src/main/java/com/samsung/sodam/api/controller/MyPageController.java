package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.response.CounselorDetailResponse;
import com.samsung.sodam.api.response.MyNotiResponse;
import com.samsung.sodam.api.response.MyOneNotiResponse;
import com.samsung.sodam.api.service.CounselorProfileService;
import com.samsung.sodam.api.service.MyPageServiceImpl;
import com.samsung.sodam.db.converter.NotiTypeConverter;
import com.samsung.sodam.db.entity.NotificationType;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/my-page")
public class MyPageController {

    private final MyPageServiceImpl service;
    private final CounselorProfileService counselorProfileService;

    public MyPageController(MyPageServiceImpl service, CounselorProfileService counselorProfileService) {
        this.service = service;
        this.counselorProfileService = counselorProfileService;
    }

    @ApiOperation(value = "상담사 상세 정보를 조회 - 상담사 본인이 조회")
    @GetMapping("/counselor/{id}")
    //상담사 정보 조회
    public ResponseEntity<CounselorDetailResponse> getCounselorInfo(@PathVariable String id) {
        try {
            return new ResponseEntity<>(counselorProfileService.getCounselorDetailAll(id), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/notification")
    @ApiOperation(value = "내게 온 알림 전체목록", notes = "내게 온 모든 알림 전체 목록 (상담, 고민댓글, 문의댓글, STT)")
    public ResponseEntity<Page<MyNotiResponse>> getAllNotification(@RequestParam(name = "type", required = false) Integer type,
                                                                   @AuthenticationPrincipal UserDetails User, Pageable pageable) {
        try {
            NotiTypeConverter notiTypeConverter = new NotiTypeConverter();
            NotificationType TYPE = notiTypeConverter.convertToEntityAttribute(type);
            Page<MyNotiResponse> list = service.getAllMyNoti(User.getUsername(), TYPE, pageable);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/notification/{id}")
    @ApiOperation(value = "내게 온 알림 상세조회", notes = "내게 온 모든 알림 상세정보")
    public ResponseEntity<MyOneNotiResponse> getOneNotification(@AuthenticationPrincipal UserDetails User, @PathVariable Long id) {
        try {
            MyOneNotiResponse noti = service.getOneMyNoti(id);
            return new ResponseEntity<>(noti, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping(value = "/notification/{id}")
    @ApiOperation(value = "하나의 알림 삭제", notes = "목록, 상세조회에서 해당 알림 삭제")
    public ResponseEntity<MyOneNotiResponse> deleteOneNotification(@AuthenticationPrincipal UserDetails User, @PathVariable Long id) {
        try {
            service.deleteOneMyNoti(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(value = "/unread-noti")
    @ApiOperation(value = "미확인 알림 갯수 조회", notes = "로그인한 유저가 미확인 알림 갯수를 조회")
    public ResponseEntity<Integer> unreadNotiCount(@AuthenticationPrincipal UserDetails user) {
        try {
            Integer count = service.unreadNotiCount(user.getUsername());
            return new ResponseEntity<>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
