package com.account.accountbook.library.exception;

import org.springframework.http.HttpStatus;

/************************************************
 * CustomErrorCode의 메소드로 추상화할 인터페이스 정의
 ************************************************/
public interface ErrorCode {

    String name();

    HttpStatus getHttpStatus();

    String getMessage();
}
