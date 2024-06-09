package com.account.accountbook.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberBudget extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_budget_idx")
    private long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @ColumnDefault("0") @Comment("예산")
    private int money;

    @Comment("목표 날짜")
    private LocalDate budgetDate;
}
