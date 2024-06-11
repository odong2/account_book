package com.account.accountbook.controller;

import com.account.accountbook.model.dto.TestDto;
import com.account.accountbook.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;
import static com.account.accountbook.library.util.response.ResponseUtil.success;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public ResponseEntity<Object> getMember(@RequestBody @Valid TestDto testDto) {
        return success(SEARCH_SUCCESS.getMessage(), testService.getMember(testDto));
    }
}
