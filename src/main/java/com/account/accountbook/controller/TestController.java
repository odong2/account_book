package com.account.accountbook.controller;

import com.account.accountbook.library.util.response.CustomResponse;
import com.account.accountbook.model.dto.TestDto;
import com.account.accountbook.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.account.accountbook.library.util.response.CustomResponse.createSuccess;
import static com.account.accountbook.library.util.response.CustomResponseCode.SEARCH_SUCCESS;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping
    public CustomResponse<TestDto> getMember(@RequestBody @Valid TestDto testDto) {
        return createSuccess(SEARCH_SUCCESS.getMessage(), testService.getMember(testDto));
    }
}
