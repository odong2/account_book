package com.account.accountbook.service.login;

import com.account.accountbook.domain.dto.login.SocialLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final HttpSession session;


    public void socialLogin(SocialLoginDto socialLoginDto, HttpServletResponse httpResponse) {

        socialLoginDto.getAccessToken();
    }
}
