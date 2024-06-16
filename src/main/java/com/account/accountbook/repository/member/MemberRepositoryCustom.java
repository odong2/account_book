package com.account.accountbook.repository.member;

import com.account.accountbook.domain.dto.oauth.MemberDto;

public interface MemberRepositoryCustom {
    MemberDto searchLoginMember(String id);
}
