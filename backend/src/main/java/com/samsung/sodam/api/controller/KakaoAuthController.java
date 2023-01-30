package com.samsung.sodam.api.controller;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.api.response.AuthKakaoResponse;
import com.samsung.sodam.api.service.AuthService;
import com.samsung.sodam.api.service.KakaoAuthService;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Member;
import com.samsung.sodam.db.entity.Role;
import com.samsung.sodam.jwt.KakaoUser;
import com.samsung.sodam.jwt.TokenDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/auth/kakao")
@CrossOrigin(
        origins = {"https://kauth.kakao.com/oauth"},
        allowCredentials = "true",
        allowedHeaders = "*",
        methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
)
@AllArgsConstructor
public class KakaoAuthController {

    private final String TRUE_STRING = "true";
    private final String FALSE_STRING = "false";
    private final KakaoAuthService kakaoService;

    private final AuthService authService;
    @PostMapping("/get-token")
    public ResponseEntity<AuthKakaoResponse> login(@RequestBody AuthCommonRequest request) {
        AuthKakaoResponse response = new AuthKakaoResponse(request);
        TokenDto tokenDto = kakaoService.getToken(request.getConfirmCode());
        KakaoUser user = kakaoService.getUserInfo(tokenDto.getAccessToken());

        if(user.getId() == null || user.getEmail() == null) throw new IllegalArgumentException("카카오 로그인 실패");

        Member m = authService.getMemberByEmail(user.getEmail());
        String id = null;
        if(m == null){ // 기존회원 아님
            id = "kakao_" + user.getId();
            response.setIsFirst(TRUE_STRING);

            System.out.println("first - "+id);
            user.setId(id);

            Role role = Role.find(request.getCommonCode());
            if(role == Role.COUNSELOR){
                response.setEmail(user.getEmail());
                response.setName(user.getName());
                response.setId(id);
                response.setIsInfoRequired(TRUE_STRING);
            }else if(role == Role.CLIENT) {
                m = kakaoService.clientSignupKakao(user);
            }
        } else{ // 기존 가입한 회원
            id = m.getId();
            System.out.println("prev signup - "+id);
            request.setId(id);
            request.setEmail(user.getEmail());
            AuthCommonResponse authResponse = authService.login(request, true);

            response.setEmail(user.getEmail());
            response.setId(authResponse.getId());
            response.setName(authResponse.getName());
            response.setToken(authResponse.getToken());

            System.out.println("KakaoAuthController - accessToken :" + response.getToken().getAccessToken());
            System.out.println("KakaoAuthController - refreshToken :" + response.getToken().getRefreshToken());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        if(m != null) return new ResponseEntity<>(response, HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/counselor-signup")
    public ResponseEntity<AuthKakaoResponse> counselorSignUp(@RequestBody CounselorRequest request) {
        Counselor c = kakaoService.counselorSignupKakao(request);
        AuthKakaoResponse response = new AuthKakaoResponse();
        response.setIsInfoRequired(FALSE_STRING);
        response.setName(c.getName());
        response.setId(c.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
