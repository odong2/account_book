package com.account.accountbook.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class QnaComment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "qna_comment_idx")
    private long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "qna_idx")
    private Qna qna;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Column(nullable = false)
    @Comment("내용")
    private String text;

    @ColumnDefault("1") @Comment("0: 삭제, 1: 정상")
    private int state;
}
