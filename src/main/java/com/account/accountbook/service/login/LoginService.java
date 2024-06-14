package com.account.accountbook.service.login;

import com.account.accountbook.domain.dto.oauth.KakaoToken;
import com.account.accountbook.domain.entity.JoinType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import static com.account.accountbook.domain.entity.JoinType.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    @Value("${oauth.kakao.tokenUrl}")
    String kakaoTokenUrl;
    @Value("${oauth.kakao.clientId}")
    String kakaoClientId;
    @Value("${oauth.kakao.redirect.uri}")
    String kakaoRedirectUri;
    @Value("${oauth.kakao.clientSecret}")
    String kakaoClientSecret;

    public String requestToken(String code, JoinType type) {
        String access_Token = "";
        String refresh_Token = "";
        String tokenUrl = type == KAKAO ? kakaoTokenUrl : null;
        KakaoToken kakaoToken = new KakaoToken(); // 요청받을 객체

        try {
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();

            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + kakaoClientId);
            sb.append("&redirect_uri=" + kakaoRedirectUri);
            sb.append("&code=" + code);
            sb.append("&client_secret=" + kakaoClientSecret);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            log.info("responsecode(200) : {}", responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            log.info("response body: {}", result);

            // json 파싱할 것임
            ObjectMapper mapper = new ObjectMapper();

            // kakaoToken에 result를 KakaoToken.class 형식으로 변환하여 저장
            kakaoToken = mapper.readValue(result, KakaoToken.class);
            System.out.println(kakaoToken);

            // api호출용 access token
            access_Token = kakaoToken.getAccess_token();

            // access 토큰 만료되면 refresh token사용(유효기간 더 김)
            refresh_Token = kakaoToken.getRefresh_token();

            log.info(access_Token);
            log.info(refresh_Token);

            br.close();
            bw.close();

        } catch (Exception e) {
        }
        log.info("카카오토큰생성완료");
        return access_Token;
    }

    public HashMap<String, String> requestUser(String accessToken) {

        log.info("유저정보 요청 시작");
        String strUrl = "https://kapi.kakao.com/v2/user/me"; // request를 보낼 주소
        HashMap userInfo = new HashMap<>();

        userInfo.put("accessToken", accessToken);

        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // url Http 연결 생성

            // POST 요청
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);// outputStreamm으로 post 데이터를 넘김

            // 전송할 header 작성, 인자로 받은 access_token전송
            conn.setRequestProperty("Authorization", "Bearer " + accessToken);

            // 실제 요청을 보내는 부분, 결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            log.info("requestUser의 responsecode(200이면성공): {}", responseCode);

            // 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            br.close();

            log.info("response body: {}", result);

            // Jackson으로 json 파싱할 것임
            ObjectMapper mapper = new ObjectMapper();

            // 결과 json을 HashMap 형태로 변환하여 resultMap에 담음

            HashMap<String, Object> resultMap = mapper.readValue(result, HashMap.class);
            String id =String.valueOf((Long) resultMap.get("id"));

            System.out.println(id);

            HashMap<String, Object> properties = (HashMap<String, Object>) resultMap.get("properties");
            String nickname = (String) properties.get("nickname");

            HashMap<String, Object> kakao_account = (HashMap<String, Object>) resultMap.get("kakao_account");
            String email = (String) kakao_account.get("email");

            userInfo.put("email", email);
            userInfo.put("id", id);
            userInfo.put("nickname", nickname);
        } catch (IOException e) {
            System.out.println(e);
        }
        return userInfo;
    }
}
