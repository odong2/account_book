package com.account.accountbook.service;

import com.account.accountbook.model.dto.TestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestService {

    public TestDto getMember(TestDto testDto) {
        return testDto;
    }
}
