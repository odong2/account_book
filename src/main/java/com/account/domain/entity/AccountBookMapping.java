package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class AccountBookMapping {
    @Id @GeneratedValue
    @Column(name = "account_book_mapping_idx")
    private Long idx;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_book_idx")
    @Comment("가계부")
    private AccountBook accountBook;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_group_idx")
    @Comment("가계부 분류")
    private AccountGroup accountGroup;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "property_group_idx")
    @Comment("자산 분류")
    private PropertyGroup propertyGroup;
}
