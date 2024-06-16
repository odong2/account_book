package com.account.accountbook.repository.member;

import com.account.accountbook.domain.dto.oauth.MemberDto;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public MemberDto searchLoginMember(String id) {
//        return queryFactory
//                .select(new QMemberDto);
        return null;
    }
}
