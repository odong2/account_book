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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**************************************************
 * @SlackNotification 사용 시 실행되는 공통 기능 모듈화
 **************************************************/
@Aspect
@Component
public class SlackNotificationAspect {

    private SlackApi slackApi;

    @Autowired
    public SlackNotificationAspect(SlackApi sendTest) {
        this.slackApi = sendTest;
    }

    @Async("taskExecutor") // 비동기 처리
    @Around("@annotation(com.account.accountbook.library.slack.annotation.SlackNotification)")
    public Object slackNotification(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        SlackAttachment attachment = new SlackAttachment();
        attachment.setFallback("Post");
        attachment.setColor("good");
        //attachment.setTitle("");

        List<SlackField> fields = new ArrayList<>();
        fields.add(new SlackField().setTitle("Arguments").setValue(proceedingJoinPoint.proceed().toString()));
        fields.add(new SlackField().setTitle("Method").setValue(proceedingJoinPoint.getSignature().getName()));
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
