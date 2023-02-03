package com.samsung.sodam.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 비동기 요청에 대한 처리가 아닌 단순 페이지 이동
@Controller
public class TestLoginController {
    @GetMapping(value = "/")
    public String home() {
        return "/index.html"; // static folder의 index.html로 이동
    }

    @GetMapping(value = "/login")
    public String login() {
        return "/login.html";
    }

    @GetMapping(value = "/auth/kakao/callback")
    public String register(){
        return "/register.html";
    }

    @GetMapping(value = "/test")
    public String test(){
        System.out.println("test");
        return "/index.html";
    }
    @GetMapping(value = "/user/dashboard")
    public String dashboard(){
        System.out.println("dashboard");
        return "/index.html";
    }

    @GetMapping(value = "/admin/board")
    public String setAdmin(){
        System.out.println("============================");
        System.out.println("testLoginController - admin");
        System.out.println("============================");
        return "/admin.html";
    }

    @GetMapping(value = "/user/info")
    public String info() {
        return "/user.html";
    }
}
