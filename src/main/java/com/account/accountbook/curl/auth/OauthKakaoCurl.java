package com.account.accountbook.curl.auth;

import com.account.accountbook.domain.dto.oauth.SocialToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 카카오 oauth curl 통신
 */
@FeignClient(name = "authCurlService", url = "${oauth.kakao.hostUrl}")
public interface OauthKakaoCurl {

    @PostMapping(value = "${oauth.kakao.tokenUrl}", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    ResponseEntity<SocialToken> getAccessTokenFromKakao(@RequestBody MultiValueMap<String, String> formData);
}

