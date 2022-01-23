package www.bwsensing.com;

import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import www.bwsensing.com.common.netty.NettyTcpServer;
import www.bwsensing.com.common.netty.NettyUdpServer;
import www.bwsensing.com.common.thread.NamedThreadFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 朱永杰
 */
@Slf4j
@EnableAsync
@EnableCaching
@ComponentScan(value = {"com.alibaba.cola","top.dcenter.ums","www.bwsensing.com"})
@SpringBootApplication(scanBasePackages = {"www.bwsensing.com", "com.alibaba.cola" })
@MapperScan("www.bwsensing.com.**.database")
public class SmartCloudApplication {
    /**
     * 默认线程池
     *     如果处理器无定制线程池，则使用此默认
     */
    private static final ExecutorService DEFAULT_EXECUTOR = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() + 1,
            Runtime.getRuntime().availableProcessors() + 1,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(1000),
            new NamedThreadFactory("ASYNC-NETTY-POOL"));

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        ConfigurableApplicationContext configContext = SpringApplication.run(SmartCloudApplication.class, args);
        NettyTcpServer nettyTcpServer = configContext.getBean(NettyTcpServer.class);
        NettyUdpServer nettyUdpServer = configContext.getBean(NettyUdpServer.class);
        DEFAULT_EXECUTOR.submit(nettyTcpServer::run);
        DEFAULT_EXECUTOR.submit(nettyUdpServer::run);
    }

}
