package com.account.accountbook.controller.v1.notice;

import com.account.accountbook.domain.dto.NoticeDto;
import com.account.accountbook.repository.notice.NoticeRepository;
import com.account.accountbook.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;
import static com.account.accountbook.library.util.response.ResponseUtil.success;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notices")
public class NoticeController {

    private final NoticeService noticeService;

    private final NoticeRepository noticeRepository;

    @GetMapping()
    public ResponseEntity<Object> list(Pageable pageable) {
        return success(SEARCH_SUCCESS.getMessage(),  noticeService.findNotice(pageable).map(NoticeDto::new));
    }
}
