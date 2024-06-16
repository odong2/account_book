package com.account.accountbook.domain.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"idx", "name"})
@AllArgsConstructor
@Builder
public class Member extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "member_idx", nullable = false, unique = true)
    private long idx;

    @Column(nullable = false, unique = true) @Comment("아이디")
    private String id;

    @Comment("이름")
    private String name;

    @Enumerated(EnumType.STRING)
    private JoinType provider;

    @Column(nullable = false, unique = true) @Comment("닉네임")
    private String nick;

    @Column(nullable = false, unique = true) @Comment("이메일")
    private String email;

    @Column(nullable = false) @Comment("비밀번호")
    private String password;

    @Column(nullable = false) @Comment("access_token")
    private String accessToken;

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

    // 정적 팩토리 메서드
    public static Member createNewMember() {
        return new Member();
    }


    /********************************************
     * 도메인 메서드
     ********************************************/
    public void updateAccessTokenByLogin(String accessToken) {
        this.accessToken = accessToken;
    }
}
