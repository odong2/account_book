package com.account.accountbook.controller.v1.login;

import com.account.accountbook.domain.dto.login.SocialLoginSearchDto;
import com.account.accountbook.library.util.response.CustomResponse;
import com.account.accountbook.library.util.response.CustomResponseCode;
import com.account.accountbook.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.account.accountbook.library.util.response.CustomResponseCode.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
public class LoginV1Controller {

    private final LoginService loginService;
    @PostMapping("/social")
    public <T> CustomResponse<T> login(@RequestBody SocialLoginSearchDto socialLoginDto) {
        System.out.println("socialLoginDto = " + socialLoginDto);
        // 소셜 로그인 처리
        loginService.socialLogin(socialLoginDto);

        return CustomResponse.createSuccessWithNoData(SUCCESS.getMessage());
    }

    @GetMapping("/session/test")
    public String test(HttpSession session) {
        System.out.println("userInfo = " + session.getAttribute("user"));

        return "success";
    }
}
