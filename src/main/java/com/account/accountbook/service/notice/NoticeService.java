package com.account.accountbook.service.notice;

import com.account.accountbook.domain.entity.Notice;
import com.account.accountbook.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<Notice> findNotice(Pageable pageable) {
        return noticeRepository.findAll(pageable);
    }



}
