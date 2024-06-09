package com.account.accountbook.controller;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.nio.charset.Charset;

/*************************
 * 응답 형식 정의 클래스
 * 상태코드, 메세지, 데이터
 *************************/
@Data
public class Response {

    private HttpStatus status;
    private String message;
    private Object data;

    public Response() {
        this.status = HttpStatus.OK;
        this.message = null;
        this.data = null;
    }

    public ResponseEntity<Response> setResponse(Response response) {

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        // return value
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
