package com.account.accountbook.repository.member;

import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.account.accountbook.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    Optional<Member> findSocialMemberById(String id);


    @Query(value = "select new com.account.accountbook.domain.dto.oauth.MemberDto(m.id, m.name) from Member m where m.accessToken = :accessToken and m.id = :id")
    Optional<MemberDto> findMember(@Param("accessToken") String accessToken, @Param("id") String id);
}
