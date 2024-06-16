package com.account.accountbook.domain.dto.oauth;

import com.account.accountbook.domain.entity.JoinType;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SocialFormDto {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String nickname;
    @NotBlank
    private String email;
    private JoinType provider;
}
