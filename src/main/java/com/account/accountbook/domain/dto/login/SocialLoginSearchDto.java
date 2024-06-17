package com.account.accountbook.domain.dto.login;

import com.account.accountbook.domain.entity.JoinType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SocialLoginSearchDto {
    private String accessToken; // 엑세스 토큰
    private String id;          // id
    private JoinType provider;    // JoinType
}
