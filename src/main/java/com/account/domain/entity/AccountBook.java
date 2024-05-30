package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountBook extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "account_book_idx", nullable = false, unique = true)
    private Long idx;

    @ManyToOne
    @JoinColumn(name = "member_idx")
    private Member member;

    private int money;

    private String text;

    private LocalDateTime accountDate;

}
