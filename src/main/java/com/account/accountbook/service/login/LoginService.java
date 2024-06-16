package com.account.accountbook.service.login;

import com.account.accountbook.domain.dto.login.SocialLoginSearchDto;
import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.account.accountbook.library.SecurityLibrary;
import com.account.accountbook.library.exception.CustomException;
import com.account.accountbook.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

import static com.account.accountbook.library.SecurityLibrary.aesDecrypt;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class LoginService {

    private final HttpSession session;
    private final MemberRepository memberRepository;


    @SneakyThrows
    public void socialLogin(SocialLoginSearchDto searchDto) {

        String accessToken = searchDto.getAccessToken();
        String id = searchDto.getProvider() + "_" + searchDto.getId();
        Optional<MemberDto> findMemberDto = memberRepository.findMember(accessToken, id);

        MemberDto memberDto = findMemberDto.orElse(null);

        // 로그인 실패
        if (memberDto == null) {
//            throw new CustomException()
        }

        System.out.println("memberDto = " + memberDto);

        session.setAttribute("user", memberDto);
    }
}
