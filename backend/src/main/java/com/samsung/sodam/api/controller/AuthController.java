package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorSignupRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.api.service.*;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Member;
import com.samsung.sodam.jwt.JwtTokenProvider;
import com.samsung.sodam.jwt.TokenDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.StringTokenizer;


@RestController
@Slf4j
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final String TRUE_STRING = "true";
    private final String FALSE_STRING = "false";

    private final AuthService authService;
    private final EmailService emailService;
    private final EnterpriseService enterpriseService;
    private final KakaoAuthService kakaoService;
    private final CounselorProfileService counselorProfileService;

    private final JwtTokenProvider jwtTokenProvider;

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
    public ResponseEntity<String> counselorSignup(CounselorSignupRequest request) {
        try {
            int enterpriseIdInt =  Integer.parseInt(request.getEnterprisestr());
            enterpriseService.existByEnterpriseId(enterpriseIdInt);
            request.setEnterpriseId(enterpriseIdInt);

            Counselor c = null;
            StringTokenizer st = new StringTokenizer(request.getId(), "_");
            String prefix = st.nextToken();
            if(prefix != null && prefix.equals("kakao")) {
                c = kakaoService.counselorSignupKakao(request);
            }
            else
                c = authService.counselorSignup(request);

            // 상담사 프로필에 들어가는 정보들 저장
            counselorProfileService.uploadAssociateProfileTable(request);
            
            return new ResponseEntity<String>(c.getId(), HttpStatus.OK);
        } catch (IllegalStateException e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(request.getId(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/logout")
    public HttpStatus logout(HttpServletRequest request){
        try {
            // 헤더에서 JWT 받아옴
            String token = request.getHeader(HttpHeaders.AUTHORIZATION);
            String id = null;

            // 유효한 토큰인지 확인
            if (token != null && token.startsWith("Bearer ")) {
                token = token.substring(7);
                if (jwtTokenProvider.validateToken(token)) {
                    id = jwtTokenProvider.getUserId(token);
                    authService.logout(id);
                }
            } else {
                log.debug("JWT Token does not begin with Bearer String");
            }
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
            authService.validateDuplicateEmail(email);  // 이메일 중복 확인

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

    @PostMapping(value = "/update-pw")
    private ResponseEntity<String> updatePW(@RequestBody AuthCommonRequest request, HttpServletRequest httpRequest){
        String id = null;
        String token = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (!jwtTokenProvider.validateToken(token)) {
                return new ResponseEntity<>("재로그인해주쇼", HttpStatus.UNAUTHORIZED);
            }
        }
        try {
            id = jwtTokenProvider.getUserId(token);
            authService.confirmPassword(id, request.getPassword());
            authService.updatePassword(id, request.getNewPassword());
            return new ResponseEntity<>(null,  HttpStatus.OK);
        }catch (Exception e){
            log.info(e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/find-id")
    private ResponseEntity<AuthCommonResponse> findId(@RequestBody AuthCommonRequest request){
        AuthCommonResponse response = null;
        try {
            Member m = authService.getMemberByEmail(request.getEmail());
            response = new AuthCommonResponse();
            if(m.getName().equals(request.getName())) {
                System.out.println(m.getName());
                String maskingId = m.getId();
                int index = maskingId.length() - 3;
                maskingId = maskingId.substring(0, index);
                response.setId(maskingId + "***");
                response.setCommonCode(m.getCommonCodeId());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            System.out.println(e.getClass() + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "/find-pw")
    private HttpStatus findPw(@RequestBody AuthCommonRequest request){
        try {
            String email = request.getEmail();
            // 랜덤 비밀번호 생성
            String newPw = RandomStringUtils.randomAlphanumeric(20);
            System.out.println(newPw);
            Member m = authService.getMemberByEmail(email);

            if(m == null) return HttpStatus.FORBIDDEN;

            String id = m.getId();
            StringTokenizer st = new StringTokenizer(request.getId(), "_");
            String prefix = st.nextToken();
            if((prefix != null && prefix.equals("kakao") )|| !id.equals(request.getId())) return HttpStatus.FORBIDDEN;
            else {
                authService.updatePassword(id, newPw);
                emailService.sendEmail(email, "소담 비밀번호 재발급","임시비밀번호", newPw);
                return HttpStatus.OK;
            }

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

    @GetMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestHeader("RefreshToken") String refreshToken) {
        try {
            return new ResponseEntity<>(authService.reissue(refreshToken), HttpStatus.OK);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/out/{id}")
    public HttpStatus deleteMember(@PathVariable String id) {
        try {
            System.out.println(id);
            authService.deleteMember(id);
            return HttpStatus.OK;
        }catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.NOT_FOUND;
        }
    }

}
