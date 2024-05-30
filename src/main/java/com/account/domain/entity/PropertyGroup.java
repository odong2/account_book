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
public class PropertyGroup extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "property_group_idx")
    private Long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_idx")
    private Member member;

    @Column(nullable = false)
    @Comment("자산 이름")
    private String name;

    @Column
    @ColumnDefault("1") @Comment("상태값")
    private Integer state;
}
