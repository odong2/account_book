package com.account.accountbook.domain.dto;

import com.account.accountbook.domain.entity.Notice;
import com.account.accountbook.domain.entity.NoticeType;
import lombok.Data;

@Data
public class NoticeDto {

    private long idx;           // idx

    private NoticeType type;    // 작성 유형

    private String title;       // 제목

    private String text;        // 텍스트

    private int sort;           // 정렬 순서

    public NoticeDto(Notice notice) {
        this.idx = notice.getIdx();
        this.type = notice.getType();
        this.title = notice.getTitle();
        this.text = notice.getText();
        this.sort = notice.getSort();
    }



}
