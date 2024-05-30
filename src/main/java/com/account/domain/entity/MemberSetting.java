package com.account.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import javax.persistence.*;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberSetting extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_setting_idx")
    private Long idx;

    @Enumerated(EnumType.STRING)
    private NoticeType type;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'KO'")
    @Comment("언어 설정")
    private LangType langType;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'LIGHT'")
    @Comment("다크모드 설정")
    private LightType lightType;

}
