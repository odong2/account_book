package com.account.accountbook.domain.dto.oauth;

import com.account.accountbook.domain.entity.JoinType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SocialToken {

    private String token_type;
    private String access_token;
    private Integer expires_in;
    private String refresh_token;
    private Integer refresh_token_expires_in;
    private String scope;
}
