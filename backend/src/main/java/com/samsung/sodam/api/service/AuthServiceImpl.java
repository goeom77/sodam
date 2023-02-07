package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.db.entity.*;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.CounselorRepository;
import com.samsung.sodam.db.repository.EnterpriseRepository;
import com.samsung.sodam.db.repository.RefreshTokenRedisRepository;
import com.samsung.sodam.jwt.JwtTokenProvider;
import com.samsung.sodam.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    @Value("${jwt.token-validity-in-seconds}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

//    @Value("${jwt.refresh-token-validity-in-seconds}")
//    private long REFRESH_TOKEN_EXPIRE_TIME;    // 7 day
    private static long REFRESH_TOKEN_EXPIRE_TIME = 60 * 7;  // 7 min (for test)

    private final ClientRepository clientRepository;
    private final CounselorRepository conselorRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final StringRedisTemplate stringRedisTemplate;

    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Override
    public void validateDuplicateEmail(String email) {
        boolean existClient = clientRepository.existsByEmail(email);
        boolean existCounselor = conselorRepository.existsByEmail(email);
        if (existClient || existCounselor) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public void validateDuplicateId(String id) {
        boolean existClient = clientRepository.existsById(id);
        boolean existCounselor = conselorRepository.existsById(id);
        if (existClient || existCounselor) {
            throw new IllegalStateException("존재하는 아이디입니다");
        }
    }

    @Override
    public Member getMemberByEmail(String email) {
        Client client = clientRepository.getByEmail(email);
        Counselor counselor = conselorRepository.getByEmail(email);
        Member m = null;
        if(client != null) m = client;
        else if(counselor != null) m = counselor;
        return m;
    }

    @Override
    public void updatePassword(String id, String pw) {
        Client c1 = clientRepository.getById(id);
        Counselor c2 = conselorRepository.getById(id);
        if(c1 != null) c1.setPassword(passwordEncoder.encode(pw));
        else if(c2 != null) c2.setPassword(passwordEncoder.encode(pw));
    }

    @Override
    public TokenDto reissue(String refreshToken) {
        refreshToken = refreshToken.substring(7);
        String id = jwtTokenProvider.getUserId(refreshToken);

        // 레디스 설치 후 주석풀기
//        RefreshToken redisRefreshToken = refreshTokenRedisRepository.findById(id).orElseThrow(() -> new NoSuchElementException("user doesn't exist"));
//
//        if (refreshToken.equals(redisRefreshToken.getRefreshToken())) {
//            Long refreshExpiredTime = redisRefreshToken.getExpiration();
//            System.out.println(refreshExpiredTime);
//            TokenDto token = null;
//
//            if(refreshExpiredTime < REFRESH_TOKEN_EXPIRE_TIME / 7) {
//                token = reissueToken(refreshToken, id, true);
//                refreshTokenRedisRepository.save(new RefreshToken(id, token.getRefreshToken(), REFRESH_TOKEN_EXPIRE_TIME));
//            }
//            else token = reissueToken(refreshToken, id, false);
//
//            return token;
//        }
//        throw new IllegalArgumentException("토큰이 일치하지 않습니다.");



        // 레디스 설치 후 주석하기
        /*--------------------------------------------------------------*/
        TokenDto token = null;
        token = reissueToken(refreshToken, id, true);
        refreshTokenRedisRepository.save(new RefreshToken(id, token.getRefreshToken(), REFRESH_TOKEN_EXPIRE_TIME));
        return token;
        /*--------------------------------------------------------------*/
    }

    private TokenDto reissueToken(String refreshToken, String id, Boolean isReissueAll) {
        TokenDto tokenDto = null;
        String roleName = jwtTokenProvider.getUserRoleName(refreshToken);
        if (jwtTokenProvider.validateToken(refreshToken)) {
            if(isReissueAll)
                tokenDto = jwtTokenProvider.generateToken(id, roleName);
            else
                tokenDto = jwtTokenProvider.generateAccessToken(id, roleName);
        }
        return tokenDto;
    }

    @Override
    public Counselor counselorSignup(CounselorRequest request) {
        Enterprise e = enterpriseRepository.getReferenceById(request.getEnterpriseId());
        Counselor c = Counselor.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                //.career(request.getCareer())
                .introduce(request.getIntroduce())
                .gender(request.getGender())
                .routine(Arrays.toString(request.getRoutine()))
                .enterprise(e)
                .consultTypeList(request.getConsultType())
                .build();
        return conselorRepository.save(c);
    }


    @Override
    public Client clientSignup(ClientRequest request) {
        validateDuplicateEmail(request.getEmail());

        Client client = clientRepository.save(Client.builder()
                .id(request.getId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .email(request.getEmail())
                .tel(request.getTel())
                .profileImg(request.getProfileImg())
                .build());

        return client;
    }

    @Override
    public AuthCommonResponse login(AuthCommonRequest request, Boolean isKakaoLogin) {
        AuthCommonResponse response = new AuthCommonResponse(request);

        String encodePassword = null;
        Member member = null;

        Role role = Role.find(request.getCommonCode());

        // id로 유저 찾기
        if(role == Role.CLIENT){
            // 프론트에서 admin도 client에서 로그인하게 되어있음
            // if문 아래의 setRoleByGroupCode으로 맞춰주기
            member = clientRepository.getById(request.getId());
        }else if (role == Role.COUNSELOR){
            member = conselorRepository.getById(request.getId());
        }else {
            throw new IllegalArgumentException("로그인 실패");
        }

        encodePassword = member.getPassword();
        member.setRoleByCommonCode();

        // 확인용 코드
        System.out.println("------------------------AuthService test log start");
        System.out.println(member.getRole().getRoleName());
        System.out.println("encodePassword - "+encodePassword);
        System.out.println("input pw - " + request.getPassword());
        System.out.println("input encode pw - " + request.getPassword());
        System.out.println("------------------------AuthService test log end");

        if(!isKakaoLogin && !passwordEncoder.matches(request.getPassword(), encodePassword)) {
            throw new IllegalArgumentException("로그인 실패");
        }
        TokenDto token = jwtTokenProvider.generateToken(member.getId(), member.getRole().getRoleName());
        response.setToken(token);
        response.setName(member.getName());
        response.setCommonCode(member.getRole().getCommonCode());

        System.out.println(jwtTokenProvider.getUserRoleName(token.getRefreshToken()));

        // 레디스 설치 후 주석풀기
        //refreshTokenRedisRepository.save(new RefreshToken(member.getId(), token.getRefreshToken(), REFRESH_TOKEN_EXPIRE_TIME));

        return response;
    }

    @Override
    public void logout(String id) {
        // 레디스 설치 후 주석풀기
        // refreshTokenRedisRepository.deleteById(id);
    }

    @Override
    public void deleteMember(String id){
        boolean existClient = clientRepository.existsById(id);
        boolean existCounselor = conselorRepository.existsById(id);
        if (!existClient && !existCounselor) {
            throw new IllegalStateException("no id match");
        }
        else if(existClient)
            clientRepository.deleteById(id);
        else if(existCounselor)
            conselorRepository.deleteById(id);


    }

}
