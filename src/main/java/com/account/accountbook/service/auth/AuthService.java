package com.account.accountbook.service.auth;

import com.account.accountbook.curl.auth.OauthKakaoCurl;
import com.account.accountbook.curl.auth.RequestUserKakaoCurl;
import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.account.accountbook.domain.dto.oauth.SocialFormDto;
import com.account.accountbook.domain.dto.oauth.SocialToken;
import com.account.accountbook.domain.entity.JoinType;
import com.account.accountbook.domain.entity.Member;
import com.account.accountbook.repository.member.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

import static com.account.accountbook.domain.entity.JoinType.*;
import static com.account.accountbook.library.SecurityLibrary.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AuthService {
    private final OauthKakaoCurl oauthKakaoCurl;
    private final RequestUserKakaoCurl requestUserKakaoCurl;

    private final MemberRepository memberRepository;

    // ObjectMapper 주입
    private final ObjectMapper objectMapper;

    @Value("${oauth.kakao.tokenUrl}")
    String kakaoTokenUrl;    // 인가 코드 url
    @Value("${oauth.kakao.clientId}")
    String kakaoClientId;    // 엑세스 토큰 url
    @Value("${oauth.kakao.redirect.uri}")
    String kakaoRedirectUri;
    @Value("${oauth.kakao.clientSecret}")
    String kakaoClientSecret;


    /**
     * 인가 코드로 엑세스 토큰 요청
     *
     * @param code
     * @param provider
     * @return
     */
    public String requestToken(String code, JoinType provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", kakaoClientId);
        formData.add("redirect_uri", kakaoRedirectUri);
        formData.add("code", code);
        ResponseEntity<SocialToken> responseEntity = null;

        if (provider.equals(KAKAO)) {
            responseEntity = oauthKakaoCurl.getAccessTokenFromKakao(formData);
        }

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            // TODO: 에러 처리
        }

        SocialToken socialToken = responseEntity.getBody();

        log.info("{} 토큰 생성 완료", provider);
        return socialToken.getAccess_token();
    }
    @SneakyThrows
    @Transactional
    public MemberDto requestUser(String accessToken, JoinType provider) {
        ResponseEntity<String> responseEntity = null;
        MemberDto memberDto = null;

        if (provider.equals(KAKAO)) {
            responseEntity = requestUserKakaoCurl.getAccessTokenFromKakao("Bearer " + accessToken);
        }

        if (responseEntity.getStatusCode() != HttpStatus.OK) {
            // TODO: 에러처리
        }
        String body = responseEntity.getBody();

        HashMap<String, Object> resultMap = objectMapper.readValue(body, HashMap.class);
        HashMap<String, Object> properties = (HashMap<String, Object>) resultMap.get("properties");

        String id = String.valueOf(resultMap.get("id"));
        String nickname = (String) properties.get("nickname");
        String profileImage = (String) properties.get("profile_image");

        // 이미 회원인 경우
        Optional<Member> findMember = memberRepository.findSocialMemberById(provider + "_" + id);

        // 회원이 아닌 경우
        Member member = findMember.orElseGet(Member::createNewMember);

        memberDto = MemberDto.builder()
                .nickname(nickname)
                .id(id)
                .provider(provider)
                .profileImage(profileImage)
                .isExistMember(false)
                .build();

        // 기존 회원인 경우 access_token 업데이트
        if (member.getIdx() > 0) {
            // 보안을 위해 암호화 하여 저장
            String encryptedToken = aesEncrypt(accessToken);
            member.updateAccessTokenByLogin(encryptedToken);
            memberDto.setIsExistMember(true);
            memberDto.setAccessToken(encryptedToken);
        }
        System.out.println("memberDto = " + memberDto);

        return memberDto;
    }

    /**
     * 소셜 회원가입 처리
     *
     * @param socialFormDto
     */
    @SneakyThrows
    @Transactional
    public void socialSignup(SocialFormDto socialFormDto) {
        Member member = Member.builder()
                .id(socialFormDto.getProvider() + "_" + socialFormDto.getId())
                .name(socialFormDto.getName())
                .email("이메일")
                .nick(socialFormDto.getNickname())
                .password(aesEncrypt("sdsdsdsdsd"))
                .lastLoginDate(LocalDateTime.now())
                .provider(socialFormDto.getProvider())
                .state(1)
                .accessToken("")
                .build();

        memberRepository.save(member);
    }
}
