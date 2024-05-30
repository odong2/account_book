package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue
    @Column(name = "member_idx", nullable = false, unique = true)
    private Long idx;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private JoinType type;

    @Column(nullable = false, unique = true) @Comment("닉네임")
    private String nick;

    @Column(nullable = false, unique = true) @Comment("이메일")
    private String email;

    @Column(nullable = false) @Comment("비밀번호")
    private String password;

    @Column
    @ColumnDefault("0") @Comment("자산")
    private Integer property;

    @Column
    @ColumnDefault("0") @Comment("부채")
    private Integer debt;

    @Column()
    @ColumnDefault("1") @Comment("상태값")
    private Integer state;


    private LocalDateTime lastLoginDate;

    //==생성 메서드==//
    //==비즈니스 로직==//
    //==연관관계 편의 메서드==//

}
