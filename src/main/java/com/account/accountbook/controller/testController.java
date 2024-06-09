package com.account.accountbook.controller;

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
    public ResponseEntity<Response> getMember(@RequestBody @Valid TestDto testDto) {

        // set result
        Response response = new Response();
        response.setMessage("성공 코드");
        response.setData(testDto);
        return setResponse(response);
    }
}
