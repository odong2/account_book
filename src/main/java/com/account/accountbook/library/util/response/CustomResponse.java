package com.account.accountbook.library.util.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.account.accountbook.library.util.response.CustomResponseCode.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CustomResponse<T> {

    private String status;
    private T data;
    private String message;
    private int code;

    /**
     * 성공(data 포함)
     **/

    public static <T> CustomResponse<T> createSuccess(String message, T data) {
        return new CustomResponse<>(SUCCESS.getMessage(), HttpStatus.OK.value(), message, data);
    }

    /**
     * 성공(data 미포함)
     **/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public static <T> CustomResponse<T> createSuccessWithNoData(String message) {
        return new CustomResponse<>(SUCCESS.getMessage(), HttpStatus.OK.value(), message, null);
    }

    /**
     * 실패
     **/
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public static <T> CustomResponse <T> createError(String message) {
        return new CustomResponse<>(ERROR.getMessage(), HttpStatus.BAD_REQUEST.value(), message, null);
    }

    /**
     *  Hibernate Validator 유효성 검사 리턴
     */
    public static CustomResponse<Map<String, String>> createFail(BindingResult bindingResult) {
        Map<String, String> errors = new HashMap<>();

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        for (ObjectError error : allErrors) {
            if (error instanceof FieldError) {
                errors.put(((FieldError) error).getField(), error.getDefaultMessage());
            } else {
                errors.put(error.getObjectName(), error.getDefaultMessage());
            }
        }

        String message = EXCEPTION.getMessage();
        int status = HttpStatus.BAD_REQUEST.value();

        return new CustomResponse<>(message, status, null, errors);
    }

    public CustomResponse(String status, int code, String message, T data) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
