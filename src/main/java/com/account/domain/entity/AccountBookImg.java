package com.account.domain.entity;

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
public class AccountBookImg extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "account_book_img_idx")
    private Long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_book_idx")
    private AccountBook accountBook;

    @Column(nullable = false)
    @Comment("이미지 url")
    private String url;

    @Column(nullable = false)
    @Comment("이미지 이름")
    private String uploadName;

    @Column(nullable = false)
    @Comment("업로드 경로")
    private String uploadPath;

    @Column @ColumnDefault("1")
    @Comment("정렬")
    private Integer sort;

    @Column @ColumnDefault("1")
    @Comment("0 : 삭제, 1: 사용")
    private Integer state;

}
