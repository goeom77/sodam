package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.RoomRequest;
import com.samsung.sodam.api.service.RoomServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/room")
public class RoomController {

    private final RoomServiceImpl service;

    public RoomController(RoomServiceImpl service) {
        this.service = service;
    }

    @PostMapping(value = "/session")
    @ApiOperation(value = "화상상담 세션 생성", notes = "상담사가 상담일정(30분)전에 세션을 생성")
    public HttpStatus createSession(@RequestBody RoomRequest request) {
        try {
            service.createSession(request);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @DeleteMapping(value = "/session")
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

    @PostMapping(value = "/recordings/start/{sessionId}")
    @ApiOperation(value = "녹음 시작", notes = "상담사가 녹음버튼을 누르면 녹음이 시작된다")
    public HttpStatus startRecord(@AuthenticationPrincipal UserDetails user, @PathVariable String sessionId) {
        try {
            service.startRecord(sessionId);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @PostMapping(value = "/recordings/stop/{sessionId}")
    @ApiOperation(value = "녹음 중지", notes = "상담사가 녹음중지 버튼을 누르면 녹음이 중지된다")
    public HttpStatus stopRecord(@PathVariable String sessionId) {
        try {
            service.stopRecord(sessionId);
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.UNAUTHORIZED;
        }
    }

}
