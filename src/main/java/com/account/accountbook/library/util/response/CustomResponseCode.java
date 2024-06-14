package com.account.accountbook.library.util.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/****************************************
 * 공통 응답 코드
 ****************************************/
@Getter
@RequiredArgsConstructor
public enum CustomResponseCode implements ResponseCode {

    // common
    SUCCESS("success"),
    ERROR("error"),
    EXCEPTION("exception"),
    SEARCH_SUCCESS("Search Complete"),
    REGISTER_SUCCESS("Register Complete"),
    UPDATE_SUCCESS("Update Complete"),
    DELETE_SUCCESS("Delete Complete"),
    ;

    private final String message;
}
