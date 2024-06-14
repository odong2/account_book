package com.account.accountbook.controller.v1.oauth;

import com.account.accountbook.library.util.response.CustomResponse;
import com.account.accountbook.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;

import static com.account.accountbook.domain.entity.JoinType.KAKAO;
import static com.account.accountbook.library.util.response.CustomResponse.*;
import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/oauth")
public class OauthV1Controller {

    private final LoginService loginService;
    @GetMapping("/kakao-login")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<HashMap<String,String>> kakaoLogin(String code) {
        log.info("code:{}", code);
        // 토큰 요청하여 얻음
        String kakaoToken = loginService.requestToken(code, KAKAO);
        System.out.println("kakaoToken = " + kakaoToken);

        // 사용자 정보 요청
        HashMap<String, String> userInfo = loginService.requestUser(kakaoToken);
        System.out.println("userInfo = " + userInfo);

        return createSuccess(SEARCH_SUCCESS.getMessage(), userInfo) ;
    }
}
