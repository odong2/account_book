package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountGroup extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "account_group_idx")
    private long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Column(nullable = false)
    @Comment("그룹 이름")
    private String name;

    @Enumerated(EnumType.STRING)
    @Comment("income, expense, transfer")
    private AccountType type;

    @ColumnDefault("1") @Comment("상태값")
    private int state;

}
