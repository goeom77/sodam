package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.LoginRequest;
import com.samsung.sodam.api.service.AuthService;
import com.samsung.sodam.jwt.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping(value = "/signup/client")
    //@ApiOperation(value="고객 회원가입", notes="새로운 고객 회원가입")
    public ResponseEntity<String> clientSignup(@RequestBody ClientRequest request) {
        try{
            authService.clientSignup(request);
            return new ResponseEntity<String>(request.getId(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/login")
    //@ApiOperation(value="로그인", notes="아이디, 비밀번호로 로그인")
    public ResponseEntity<TokenDto>  login(@RequestBody LoginRequest request)        {
        TokenDto dto = authService.login(request);
        System.out.println("AuthController - accessToken :" + dto.getAccessToken());
        System.out.println("AuthController - refreshToken :" + dto.getRefreshToken());
t p
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value="/check-duplicate-id/{id}")
    public HttpStatus checkDuplicate(@PathVariable String id) {
        try {
            authService.validateDuplicateId(id);
            return HttpStatus.OK;
        } catch (Exception e){
                System.out.println(e.getMessage());
                return HttpStatus.CONFLICT;
        }
    }

}
