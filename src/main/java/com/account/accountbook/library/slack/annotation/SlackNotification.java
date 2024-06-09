package com.account.accountbook.library.slack.annotation;

import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/***********************************************
 * Slack 전송용 커스텀 어노테이션 생성
 * 메소드 단위로 선언하여 사용(=메소드의 실행 결과 전송)
 ***********************************************/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SlackNotification {
}
