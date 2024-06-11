package com.account.accountbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/*******************************************************
 * slack에서 webhook에 알림을 요청하고 응답을 받는 과정은
 * 비즈니스 로직 수행 시간에 영향이 없도록 별도의 쓰레드에서 비동기 처리
 *******************************************************/
@Configuration
@EnableAsync // 비동기 처리
public class ThreadPoolConfig {

    // 동시에 실행 할 기본 스레드의 수(기본값 : 1)
    private static final int CORE_POOL_SIZE = 5;

    // thread-pool의 사용할 수 있는 최대 스레드 수(기본값 : Integer.MAX_VALUE)
    private static final int MAX_POOL_SIZE = 5;

    // thread-pool executor의 작업 큐의 크기(기본값 : Integer.MAX_VALUE)
    private static final int QUEUE_CAPACITY = 100;

    // true 설정시 어플리케이션 종료 요청시 queue에 남아 있는 모든 작업들이 완료될 때까지 기다린 후 종료
    private static final boolean WAIT_TASK_COMPLETE = true;

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);

        // max size만큼 스레드를 생성하고, 설정한 queue가 가득 찬 상태에서 추가 작업이 들어올 경우
        // 더 이상 처리할 수 없다는 RejectedExecutionException 예외 방지 처리
        // 예외와 작업의 누락 없이 모두 처리하기 위해 CallerRunsPolicy 옵션 선택
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // 작업 도중에 어플리케이션 종료 시 아직 처리되지 못한 작업이 있을 경우 유실 방지 처리
        taskExecutor.setWaitForTasksToCompleteOnShutdown(WAIT_TASK_COMPLETE);

        taskExecutor.initialize();
        return taskExecutor;
    }
}
