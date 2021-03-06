package www.bwsensing.com.common.core.async;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author macos-zyj
 */
@Configuration
@EnableAsync
public class AsyncTaskPool {

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 设置核心线程数
        executor.setCorePoolSize(Runtime.getRuntime().availableProcessors() + 1);
        // 设置最大线程数
        executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors() + 1);
        // 设置队列容量
        executor.setQueueCapacity((Runtime.getRuntime().availableProcessors() + 1) * 2);
        // 设置线程活跃时间（秒）
        executor.setKeepAliveSeconds(5);
        // 设置默认线程名称
        executor.setThreadNamePrefix("async-task-");
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        // 等待所有任务结束后再关闭线程池
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }
}