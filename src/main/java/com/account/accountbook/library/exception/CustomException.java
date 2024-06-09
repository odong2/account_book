package com.account.accountbook.library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/***********************************************
 * 언체크 예외(런타임 예외)를 상속받는 예외 클래스 정의
 ***********************************************/
@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

    private final ErrorCode errorCode;

}
