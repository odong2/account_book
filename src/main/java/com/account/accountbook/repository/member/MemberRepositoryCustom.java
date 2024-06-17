package com.account.accountbook.repository.member;

import com.account.accountbook.domain.dto.oauth.MemberDto;

import java.util.Optional;

public interface MemberRepositoryCustom {
    Optional<MemberDto> findMemberDtoByIdAndToken(String id, String accessToken);
}
