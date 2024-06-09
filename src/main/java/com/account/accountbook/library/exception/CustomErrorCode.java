package com.account.accountbook.library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

/****************************************
 * 에러 코드
 ****************************************/
@Getter
@RequiredArgsConstructor
public enum CustomErrorCode implements ErrorCode {

    // common
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "Invalid parameter included"),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "Resource not exists"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"),

    // user
    INACTIVE_USER(HttpStatus.FORBIDDEN, "User is inactive"),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
