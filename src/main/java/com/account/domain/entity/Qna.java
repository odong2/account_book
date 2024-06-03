package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Qna extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "qna_idx")
    private long idx;

    @OneToMany(mappedBy = "qna", cascade = CascadeType.ALL, orphanRemoval = true)
    @Comment("qna 댓글 및 답변")
    private List<QnaComment> qnaComments = new ArrayList<>();

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Column(nullable = false)
    @Comment("제목")
    private String title;

    @Column(nullable = false)
    @Comment("내용")
    private String text;

    @ColumnDefault("1")
    @Comment("0: 삭제, 1: 미답변, 2: 답변완료")
    private int state;
}
