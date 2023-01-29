package com.samsung.sodam.api.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.samsung.sodam.api.request.AuthCommonRequest;
import com.samsung.sodam.api.request.ClientRequest;
import com.samsung.sodam.api.request.CounselorRequest;
import com.samsung.sodam.api.response.AuthCommonResponse;
import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.jwt.KakaoUser;
import com.samsung.sodam.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class KakaoAuthServiceImpl implements KakaoAuthService{

    @Value("${auth.kakao.client-id}")
    private String CLIENT_ID;

    @Value("${auth.kakao.redirect-uri}")
    private String REDIRECT_URI;

    @Value("${auth.kakao.authorize-uri}")
    private String AUTHORIZE_URI;

    @Value("${auth.kakao.token-uri}")
    public String TOKEN_URI;

    private final AuthService authService;


    @Override
    public TokenDto getToken(String code) {
        String access_Token="";
        String refresh_Token ="";
        String accessTokenExpiresIn = null;


        try{
            long now = (new Date()).getTime();

            URL url = new URL(TOKEN_URI);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=").append(CLIENT_ID); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=").append(REDIRECT_URI); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            sb.append("&scope=account_email,profile_nickname,profile_image");
            bw.write(sb.toString());
            bw.flush();


            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);
            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            int expiresIn = element.getAsJsonObject().get("expires_in").getAsInt();
            Date accessTokenExpiresInDate = new Date(now + expiresIn); SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd/HH:mm:ss");
            //원하는 데이터 포맷 지정
            accessTokenExpiresIn = simpleDateFormat.format(accessTokenExpiresInDate);


            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);
            System.out.println("accessTokenExpiresIn : " + accessTokenExpiresIn);

            br.close();
            bw.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
        return new TokenDto(access_Token, refresh_Token, accessTokenExpiresIn);
    }


    public KakaoUser getUserInfo(String accessToken){

        KakaoUser user = new KakaoUser();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // 요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);
            System.out.println("result type" + result.getClass().getName()); // java.lang.String

            try {


                JsonParser parser = new JsonParser();
                JsonElement element =  parser.parse(result);

                JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
                JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

                user.setId(element.getAsJsonObject().get("id").getAsString());
                user.setName(properties.getAsJsonObject().get("nickname").getAsString());
                user.setEmail(kakaoAccount.getAsJsonObject().get("email").getAsString());
                if(!kakaoAccount.getAsJsonObject().get("profile_image_needs_agreement").getAsBoolean())
                    user.setThumbnailImage(kakaoAccount.getAsJsonObject().get("profile").getAsJsonObject().get("thumbnail_image_url").getAsString());

                System.out.println(user.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Client clientSignupKakao(KakaoUser user) {
        return authService.clientSignup(new ClientRequest(user.getId(), "NO_PASSWORD", user.getName(), null, user.getEmail(), user.getThumbnailImage()));
    }

    @Override
    public AuthCommonResponse kakaoLogin(AuthCommonRequest request) {

        return null;
    }

    @Override
    public Counselor counselorSignupKakao(CounselorRequest request) {
        request.setPassword("NO_PASSWORD");
        return authService.counselorSignup(request);
    }


}
