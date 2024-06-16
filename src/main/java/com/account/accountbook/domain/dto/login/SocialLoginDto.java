package com.account.accountbook.domain.dto.login;

import lombok.Data;

@Data
public class SocialLoginDto {
    private String accessToken;
    private String id;
    private String provider;
}
