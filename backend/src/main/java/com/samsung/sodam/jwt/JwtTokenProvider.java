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
import java.text.SimpleDateFormat;
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

    @Value("${jwt.token-validity-in-seconds}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    //private static final long REFRESH_TOKEN_EXPIRE_TIME = 60 * 7;  // 7 min (for test)

    private final UserDetailsServiceImpl userDetailsService;

    /**
     * accessToken, RefreshToken 발급
     * */
    public TokenDto generateToken(String id, String roleName) {

        Map<String, Object> claims = new HashMap<>();
        claims.put("role", roleName); // 정보는 key / value 쌍으로 저장된다.

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresInDate = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = doGenerateToken(claims, id, ACCESS_TOKEN_EXPIRE_TIME);

        // Refresh Token 생성
        String refreshToken = doGenerateToken(claims,id, REFRESH_TOKEN_EXPIRE_TIME);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd/HH:mm:ss");
        //원하는 데이터 포맷 지정
        String accessTokenExpiresIn = simpleDateFormat.format(accessTokenExpiresInDate);

        return new TokenDto(accessToken, refreshToken, accessTokenExpiresIn);
    }

    /**
     * accessToken만 발급
     * */
    public TokenDto generateAccessToken(String id, String roleName) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", roleName); // 정보는 key / value 쌍으로 저장된다.

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresInDate = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = doGenerateToken(claims, id, ACCESS_TOKEN_EXPIRE_TIME);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd/HH:mm:ss");
        String accessTokenExpiresIn = simpleDateFormat.format(accessTokenExpiresInDate);

        return new TokenDto(accessToken, null, accessTokenExpiresIn);

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
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }


    // Jwt 토큰을 Claim 으로 변경
    private Claims getClaim(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public String getUserRoleName(String token) {
        return (String) Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("role");
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
    public Date getExpiration(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return claims.getBody().getExpiration();
        } catch (Exception e) {
            return null;
        }
    }


}