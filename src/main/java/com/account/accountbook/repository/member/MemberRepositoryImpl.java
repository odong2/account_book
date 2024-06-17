package com.account.accountbook.repository.member;

import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.account.accountbook.domain.dto.oauth.QMemberDto;
import com.account.accountbook.domain.entity.Member;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.Optional;

import static com.account.accountbook.domain.entity.QMember.*;

public class MemberRepositoryImpl implements MemberRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    /**
     * 회원 정보 조회
     *
     * @param id
     * @param accessToken
     * @return MemberDto
     */
    @Override
    public Optional<MemberDto> findMemberDtoByIdAndToken(String id, String accessToken) {
        return Optional.ofNullable(
                queryFactory
                    .select(new QMemberDto(member.id, member.name, member.email, member.provider, member.nick))
                    .from(member)
                    .where(
                        member.accessToken.eq(accessToken),
                        member.id.eq(member.id))
                .fetchOne()
        );
    }

//    @Override
//    public Optional<Member> findSocialMemberById(String id) {
//        // bean(setter 주입), fields(필드 주입), constructor(생성자 주입)
//        Member memberEntity = queryFactory
//                .select(
//                        Projections.constructor(Member.class,
//                                member.idx)
//                )
//                .from(member)
//                .where(member.id.eq(member.id))
//                .fetchOne();
//
//
//        return Optional.ofNullable(memberEntity);
//    }

}
