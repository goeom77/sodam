package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.api.service.RoomServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/room")
public class RoomController {

    private final RoomServiceImpl service;

    public RoomController(RoomServiceImpl service) {
        this.service = service;
    }

    @PostMapping(name = "")
    @ApiOperation(value = "화상상담 세션 생성", notes = "상담사가 상담일정(30분)전에 세션을 생성")
    public HttpStatus createSession(@AuthenticationPrincipal UserDetails user, @RequestBody RoomRequest request) {
        try {
            service.createSession(request);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @DeleteMapping(name = "")
    @ApiOperation(value = "화상상담 세션 삭제", notes = "상담사가 상담을 종료하면 해당 화상상담 세션 삭제")
    public HttpStatus deleteSession(@AuthenticationPrincipal UserDetails user, @RequestBody RoomRequest request) {
        try {
            service.deleteSession(request);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.UNAUTHORIZED;
        }
    }

}
