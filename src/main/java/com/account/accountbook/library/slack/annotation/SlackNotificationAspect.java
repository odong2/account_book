package com.account.accountbook.library.slack.annotation;

import com.account.accountbook.library.slack.SlackApi;
import com.account.accountbook.library.slack.SlackAttachment;
import com.account.accountbook.library.slack.SlackField;
import com.account.accountbook.library.slack.SlackMessage;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.*;

/**************************************************
 * @SlackNotification 사용 시 실행되는 공통 기능 모듈화
 **************************************************/
@Aspect
@Component
public class SlackNotificationAspect {

    private final SlackApi slackApi;

    @Autowired
    public SlackNotificationAspect(SlackApi sendTest) {
        this.slackApi = sendTest;
    }

    @Async("taskExecutor") // 비동기 호출
    @Around("@annotation(com.account.accountbook.library.slack.annotation.SlackNotification)")
    public Object slackNotification(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        SlackAttachment attachment = new SlackAttachment();
        attachment.setFallback("Post");
        attachment.setColor("good");
        //attachment.setTitle("");

        // AOP -> HttpServletRequest 접근 시도(RequestURL 출력 목적) -> 쓰레드가 달라서 조회 불가 -> 필요시 재시도
//        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        List<SlackField> fields = new ArrayList<>();
//        fields.add(new SlackField().setTitle("Request URL").setValue(req.getRequestURL().toString()));
        fields.add(new SlackField().setTitle("Request Method").setValue(proceedingJoinPoint.getSignature().getName()));
        fields.add(new SlackField().setTitle("Response").setValue(Arrays.toString(proceedingJoinPoint.getArgs())));
        fields.add(new SlackField().setTitle("Timestamp").setValue(new Date().toString()));
        attachment.setFields(fields);

        SlackMessage message = new SlackMessage();
        message.setAttachments(Collections.singletonList(attachment));
        message.setIcon(":floppy_disk:");
        message.setText("Check method result!");
        message.setUsername("TestBot");
        slackApi.call(message);

        return proceedingJoinPoint.proceed();
    }
}
