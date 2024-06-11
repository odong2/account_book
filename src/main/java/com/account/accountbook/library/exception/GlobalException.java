package com.account.accountbook.library.exception;

import com.account.accountbook.library.slack.SlackApi;
import com.account.accountbook.library.slack.SlackAttachment;
import com.account.accountbook.library.slack.SlackField;
import com.account.accountbook.library.slack.SlackMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.account.accountbook.library.util.response.ResponseUtil.EXCEPTION;

@RestControllerAdvice
@Slf4j
public class GlobalException extends ResponseEntityExceptionHandler {

    /** slack **/
    private SlackApi slackApi;

    @Autowired
    public GlobalException(SlackApi sendError) {
        this.slackApi = sendError;
    }

    /****************************************
     * 예외 처리
     ****************************************/
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(HttpServletRequest req, Exception e) {
        sendSlackMessage(req, e);
        log.warn("handleAllException", e);
        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode);
    }

    // 런타임 오류
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(HttpServletRequest req, RuntimeException e) {
        sendSlackMessage(req, e);
        log.warn("handleRuntimeException", e);
        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(HttpServletRequest req, CustomException e) {
        sendSlackMessage(req, e);
        log.warn("handleCustomException", e);
        ErrorCode errorCode = e.getErrorCode();
        return handleExceptionInternal(errorCode);
    }

    // 파라미터 오류
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(HttpServletRequest req, IllegalArgumentException e) {
        sendSlackMessage(req, e);
        log.warn("handleIllegalArgument", e);
        ErrorCode errorCode = CustomErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    // 파라미터 타입 오류
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
        sendSlackMessage(req, e);
        log.warn("handleMethodArgumentTypeMismatch", e);
        ErrorCode errorCode = CustomErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    // 바인딩 오류
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(HttpServletRequest req, BindException e) {
        sendSlackMessage(req, e);
        log.warn("handleBindException", e);
        ErrorCode errorCode = CustomErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    // SQL 관련 오류
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(HttpServletRequest req, SQLException e) {
        sendSlackMessage(req, e);
        log.warn("handleSQLException", e);
        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    // DB 관련 오류
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleDBException(HttpServletRequest req, DataAccessException e) {
        sendSlackMessage(req, e);
        log.warn("handleDBException", e);
        ErrorCode errorCode = CustomErrorCode.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(errorCode, e.getMessage());
    }

    // @Valid에 의한 유효성 검증 실패
    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        sendSlackMessage(((ServletWebRequest)request).getRequest(), e); // 형변환(WebRequest -> HttpServletRequest)
        log.warn("handleMethodArgumentNotValid", e);
        ErrorCode errorCode = CustomErrorCode.INVALID_PARAMETER;
        return handleExceptionInternal(e, errorCode);
    }

    /** 이미 ResponseEntityExceptionHandler에 해당 exception이 구현되어 있어 충돌 발생 -> 필요 시 @Override해서 사용 **/
    // 404 에러
//    @ExceptionHandler(NoHandlerFoundException.class)
//    public ResponseEntity<Object> handleNoHandlerFound(HttpServletRequest req, NoHandlerFoundException e) {
//        sendSlackMessage(req, e);
//        log.warn("handleNoHandlerFound", e);
//        ErrorCode errorCode = CommonErrorCode.RESOURCE_NOT_FOUND;
//        return handleExceptionInternal(errorCode, e.getMessage());
//    }

    /****************************************
     * 응답 형식 세팅
     ****************************************/

    /** (ver.1) - (1) http status (2) error code (3) error message **/
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .status(EXCEPTION)
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }

    /** (ver.2) - (1) http status (2) error code (3) error message **/
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .status(EXCEPTION)
                .code(errorCode.name())
                .message(message)
                .build();
    }

    /** (ver.3) - @Valid로 인한 exception 발생 시 처리 **/
    private ResponseEntity<Object> handleExceptionInternal(MethodArgumentNotValidException e, ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));
    }

    private ErrorResponse makeErrorResponse(MethodArgumentNotValidException e, ErrorCode errorCode) {
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .status(EXCEPTION)
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }

    /****************************************
     * slack 에러 메세지 전송
     ****************************************/
    private void sendSlackMessage(HttpServletRequest req, Exception e) {

        SlackAttachment attachment = new SlackAttachment();
        attachment.setFallback("Error");
        attachment.setColor("danger");
        attachment.setTitle("Error Message");
        attachment.setTitleLink(req.getContextPath());
        //attachment.setText(e.getStackTrace()[0].toString());
        attachment.setText(e.getMessage());
        attachment.setColor("danger");

        List<SlackField> fields = new ArrayList<>();
        fields.add(new SlackField().setTitle("Request URL").setValue(req.getRequestURL().toString()));
        fields.add(new SlackField().setTitle("Request Method").setValue(req.getMethod()));
        fields.add(new SlackField().setTitle("Request Time").setValue(new Date().toString()));
        fields.add(new SlackField().setTitle("Request IP").setValue(req.getRemoteAddr()));
        fields.add(new SlackField().setTitle("Request User-Agent").setValue(req.getHeader("User-Agent")));
        attachment.setFields(fields);

        SlackMessage message = new SlackMessage();
        message.setAttachments(Collections.singletonList(attachment));
        message.setIcon(":ghost:");
        message.setText("Check error!");
        message.setUsername("ErrorBot");

        slackApi.call(message);
    }
}
