package com.account.accountbook.domain.dto.oauth;

import com.account.accountbook.domain.entity.JoinType;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
    private long idx;
    private String id;
    private String name;
    private String email;
    private JoinType provider;
    private String nickname;
    private Boolean isExistMember;
    private String accessToken;
    private String profileImage;

    public MemberDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @QueryProjection
    public MemberDto(String id, String name, String email, JoinType provider, String nickname) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
    }

    @Builder
    public MemberDto(String id, String name, String email, String nickname, JoinType provider, String accessToken, boolean isExistMember, String profileImage) {
        this.id = id;
        this.email = email;
        this.name = nickname;
        this.provider = provider;
        this.accessToken = accessToken;
        this.isExistMember = isExistMember;
        this.profileImage = profileImage;
    }


}
