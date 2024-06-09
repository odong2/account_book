package com.account.accountbook.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Role extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "role_idx")
    private long idx;

    @Column(nullable = false, unique = true) @Comment("권한이름")
    private String name;

    @ColumnDefault("1") @Comment("0 : 미사용, 1 : 사용")
    private int state;

}
