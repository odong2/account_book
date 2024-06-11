package com.account.accountbook.library.util.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*******************************************
 * 응답 형식 정의 클래스
 * 상태(status), 메세지(message), 데이터(data)
 *******************************************/
@Getter
@Builder
@RequiredArgsConstructor
public class Response {

    private final String status;

    private final int code;

    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Object data;
}
