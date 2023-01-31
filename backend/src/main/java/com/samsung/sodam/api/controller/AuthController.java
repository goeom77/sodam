package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.api.service.AuthService;
import com.samsung.sodam.api.service.EmailService;
import com.samsung.sodam.api.service.EnterpriseService;
import com.samsung.sodam.api.service.KakaoAuthService;
import com.samsung.sodam.db.entity.Counselor;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Random;

import java.util.StringTokenizer;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final String TRUE_STRING = "true";
    private final String FALSE_STRING = "false";

    private final AuthService authService;
    private final EmailService emailService;
    private final EnterpriseService enterpriseService;
    private final KakaoAuthService kakaoService;



    private String confirmCode;
    private Boolean isFindId;
    @PostMapping(value = "/signup/client")
    @ApiOperation(value="고객 회원가입", notes="새로운 고객 회원가입")
    public ResponseEntity<String> clientSignup(@RequestBody ClientRequest request) {
        try{
            authService.clientSignup(request);
            return new ResponseEntity<String>(request.getId(), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping(value = "/signup/counselor")
    @ApiOperation(value="상담사 회원가입", notes="새로운 상담사 회원가입")
    public ResponseEntity<String> counselorSignup(@RequestBody CounselorRequest request) {
        try{
            enterpriseService.existByEnterpriseId(request.getEnterpriseId());
            System.out.println("AuthController - enterpriseId: "+request.getEnterpriseId());

            Counselor c = null;
            StringTokenizer st = new StringTokenizer(request.getId(), "_");
            String prefix = st.nextToken();
            if(prefix != null && prefix.equals("kakao")) {
                c = kakaoService.counselorSignupKakao(request);
            }
            else
                c = authService.counselorSignup(request);

            System.out.println(c.toString());
            return new ResponseEntity<String>(c.getId(), HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/logout/{id}")
    public HttpStatus logout(@PathVariable String id){
        try {
            // delete refresh token
            // delete authentication (??모름)
            return HttpStatus.OK;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return HttpStatus.UNAUTHORIZED;
        }
    }

    @PostMapping(value = "/login")
    @ApiOperation(value="로그인", notes="아이디, 비밀번호로 로그인")
    public ResponseEntity<AuthCommonResponse>  login(@RequestBody AuthCommonRequest request){
        AuthCommonResponse response = authService.login(request, false);
        return new ResponseEntity<>(response, HttpStatus.OK);
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

    @PostMapping(value = "/send-code")
    private HttpStatus sendConfirmMail(@RequestBody AuthCommonRequest request){
        String email = request.getEmail();

        try {
            authService.validateDuplicateEmail(email);

            Random r = new Random();
            int checkNum = r.nextInt(888888) + 111111;
            System.out.println("인증번호 : " + checkNum);
            confirmCode = Integer.toString(checkNum);

            emailService.sendEmail(request.getEmail(), "소담 이메일 인증코드","인증코드", confirmCode);
            return HttpStatus.OK;
        }catch (IllegalStateException e){
            return HttpStatus.CONFLICT;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return HttpStatus.BAD_REQUEST;
        }
    }
    @PostMapping(value = "/find-pw")
    private HttpStatus findPw(@RequestBody AuthCommonRequest request){

        System.out.println("********************************************");
        System.out.println(request.getEmail());
        try {
            // 랜덤 비밀번호 생성
            String newpw = RandomStringUtils.randomAlphanumeric(20);
            System.out.println(newpw);
            emailService.sendEmail(request.getEmail(), "소담 비밀번호 재발급","임시비밀번호", newpw);
            return HttpStatus.OK;
        }catch (Exception e){
            e.printStackTrace();
            return HttpStatus.FORBIDDEN;
        }

    }

    @GetMapping(value = "/confirm-mail")
    private ResponseEntity<AuthCommonResponse> confirmMail(@RequestParam String code){
        AuthCommonResponse response = new AuthCommonResponse();

        if(confirmCode.equals(code)) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
