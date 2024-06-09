package com.account.accountbook.config;

import com.account.accountbook.library.slack.SlackApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    /** 에러 확인용 webhook url **/
    @Value("${slack.webhook.url.error}")
    private String errorToken;

    /** 테스트할 메소드 결과 확인용 webhook url **/
    @Value("${slack.webhook.url.test}")
    private String testToken;

    @Bean
    public SlackApi sendError() {
        return new SlackApi(errorToken);
    }

    @Bean
    public SlackApi sendTest() {
        return new SlackApi(testToken);
    }
}
