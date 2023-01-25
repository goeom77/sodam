package com.samsung.sodam.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "Authorization";
    private static final String BEARER_TYPE = "Bearer ";
    @Value("${jwt.secret-key}")
    private String secretKey;

    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24;            // 하루
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final UserDetailsServiceImpl userDetailsService;


    public TokenDto generateToken(String id, int role) {

        Map<String, Object> claims = new HashMap<>();

        String roleName = null;
        if(role == 0) roleName = Role.ADMIN.getRoleName();
        else if(role == 1) roleName = Role.COUNSELOR.getRoleName();
        else roleName = Role.CLIENT.getRoleName();


        claims.put("role", roleName); // 정보는 key / value 쌍으로 저장된다.

        // Access Token 생성
        //Date accessTokenExpiresIn = new Date(now() + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = doGenerateToken(claims, id, ACCESS_TOKEN_EXPIRE_TIME);

        // Refresh Token 생성
        String refreshToken = doGenerateToken(claims,id, REFRESH_TOKEN_EXPIRE_TIME);

        //return doGenerateToken(claims, );
        return new TokenDto(accessToken, refreshToken);
    }

    private String doGenerateToken(Map<String, Object> claims,String id, long tokenValidTime) {
        return Jwts.builder()
                .setHeaderParam("typ","JWT")
                .setClaims(claims).setSubject(id)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime * 1000))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserId(String token) {
        // test
//        System.out.println("jwtTokenProvider - " + token);
//        System.out.println(Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody());

        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옴.
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}