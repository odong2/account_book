package com.account.accountbook.controller;

import com.account.accountbook.library.response.CustomResponseCode;
import com.account.accountbook.library.response.Response;
import com.account.accountbook.library.slack.annotation.SlackNotification;
import com.account.accountbook.model.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class testController extends Response {

    @PutMapping
    @SlackNotification
    public ResponseEntity<Object> getMember(@RequestBody @Valid TestDto testDto) {

        // set result
        Response response = new Response();
        response.setMessage(CustomResponseCode.SEARCH_SUCCESS.getMessage());
        response.setData(testDto);
        return setResponse(response);
    }
}
