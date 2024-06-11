package com.account.accountbook.library.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseUtil {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    public static final String EXCEPTION = "exception";

    /** 성공(data 포함) **/
    public static ResponseEntity<Object> success(String message, Object data) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(SUCCESS, HttpStatus.OK.value(), message, data));
    }

    /** 성공(data 미포함) **/
    public static ResponseEntity<Object> successWithNoData(String message) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(SUCCESS, HttpStatus.OK.value(), message, null));
    }

    /** 실패 **/
    public static ResponseEntity<Object> error(String message) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(new Response(ERROR, HttpStatus.OK.value(), message, null));
    }

    /** 예외 : exception 클래스에서 반환 **/
}
