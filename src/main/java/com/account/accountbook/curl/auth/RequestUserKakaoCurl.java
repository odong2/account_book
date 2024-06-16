package com.account.accountbook.curl.auth;

import com.account.accountbook.domain.dto.oauth.MemberDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.HashMap;

/**
 * 카카오 사용자 정보 curl 통신
 */
@FeignClient(name = "RequestUserKakaoCurl", url = "${oauth.kakao.requestUser.hostUrl}")
public interface RequestUserKakaoCurl {
    /**
     * accessToken으로 유저 정보 조회
     *
     * @param token
     * @return
     */
    @PostMapping(value = "${oauth.kakao.requestUser.url}", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    ResponseEntity<String> getAccessTokenFromKakao(@RequestHeader("Authorization") String token);
}
