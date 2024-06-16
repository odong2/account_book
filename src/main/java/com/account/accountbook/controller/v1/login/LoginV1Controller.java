package com.account.accountbook.controller.v1.login;

import com.account.accountbook.service.login.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/login")
public class LoginV1Controller {

    private final LoginService loginService;
//    @PostMapping("/social")
//    public <T> CustomResponse <T> login(@RequestBody SocialLoginDto socialLoginDto,
//                                        HttpServletResponse httpResponse) {
//        // 소셜 로그인 처리
//        loginService.socialLogin(socialLoginDto, httpResponse);
//
//    }
}
