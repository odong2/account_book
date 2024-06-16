package com.account.accountbook.controller.v1.oauth;

import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.account.accountbook.domain.dto.oauth.SocialFormDto;
import com.account.accountbook.library.util.response.CustomResponse;
import com.account.accountbook.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.account.accountbook.domain.entity.JoinType.KAKAO;
import static com.account.accountbook.library.util.response.CustomResponse.*;
import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/oauth")
public class OauthV1Controller {

    private final AuthService authService;
    @GetMapping("/info/{social}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponse<MemberDto> getUerInfo(String code) {
        log.info("code:{}", code);
        // 인가 코드로 엑세스 토큰 요청
        String accessToken = authService.requestToken(code, KAKAO);

        // 사용자 정보 요청
        return createSuccess(SEARCH_SUCCESS.getMessage(), authService.requestUser(accessToken, KAKAO)) ;
    }

    /**
     * 소셜 회원가입
     * @param socialFormDto
     * @return
     * @param <T>
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/social/sign-up")
    public <T> CustomResponse<T> socialSignup(@RequestBody SocialFormDto socialFormDto) {
        System.out.println("socialFormDto = " + socialFormDto);

        authService.socialSignup(socialFormDto);

        return createSuccessWithNoData(SEARCH_SUCCESS.getMessage());
    }
}
