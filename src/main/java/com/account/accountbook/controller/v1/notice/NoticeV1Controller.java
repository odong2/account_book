package com.account.accountbook.controller.v1.notice;

import com.account.accountbook.domain.dto.NoticeDto;
import com.account.accountbook.library.util.response.CustomResponse;
import com.account.accountbook.repository.notice.NoticeRepository;
import com.account.accountbook.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.account.accountbook.library.util.response.CustomResponse.*;
import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notices")
public class NoticeV1Controller {

    private final NoticeService noticeService;


    @GetMapping()
    public CustomResponse<Page<NoticeDto>> list(Pageable pageable) {
        return createSuccess(SEARCH_SUCCESS.getMessage(),  noticeService.findNotice(pageable).map(NoticeDto::new));
    }
}
