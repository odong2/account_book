package com.account.accountbook.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_idx", nullable = false, unique = true)
    private long idx;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "member_setting_idx")
    private MemberSetting memberSetting;

    @Comment("이름")
    private String name;

    @Enumerated(EnumType.STRING)
    private JoinType type;

    @Column(nullable = false, unique = true) @Comment("닉네임")
    private String nick;

    @Column(nullable = false, unique = true) @Comment("이메일")
    private String email;

    @Column(nullable = false) @Comment("비밀번호")
    private String password;

    @ColumnDefault("0") @Comment("자산")
    private int property;

    @ColumnDefault("0") @Comment("부채")
    private int debt;

    @ColumnDefault("1") @Comment("상태값")
    private int state;

    @Comment("최근 로그인 일자")
    private LocalDateTime lastLoginDate;

    // 생성자에 @Builder 적용
    @Builder
    public Member(String name) {
        this.name = name;
    }
}
