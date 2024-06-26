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
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue @Column(name = "notice_idx")
    private long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @Column(nullable = false)
    @Comment("제목")
    private String title;

    @Column(nullable = false)
    @Comment("내용")
    private String text;

    @ColumnDefault("1") @Comment("정렬")
    private int sort;

    @ColumnDefault("1") @Comment("상태값")
    private int state;
}
