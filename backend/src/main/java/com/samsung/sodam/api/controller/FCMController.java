package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.service.FCMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FCMController {

    private final FCMService fcm;

    @GetMapping(value="/test-fcm")
    public void testFCM() throws IOException {

        String target = "cxlJ_8jqXCKx5YV6kYgkhp:APA91bEBHzKp_FlInhGRZEpdunXElK9Sj2EOC0TOz9Xe3-CLTC6FdbUd9GPnWXxp0X26YJfTv4Ap2Q0Bat9MZhpS36wvdbjN146pl27BLdJYI_TbKtdnEAZZQpl-Z7Ye3A4LqqGY4jk7";
        fcm.sendMessageTo(target, "알림 성공!!", "제발제발");
    }
}
