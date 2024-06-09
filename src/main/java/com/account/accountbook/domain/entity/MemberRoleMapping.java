package com.account.accountbook.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberRoleMapping extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_role_idx")
    private long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "role_idx")
    private Role role;
}
