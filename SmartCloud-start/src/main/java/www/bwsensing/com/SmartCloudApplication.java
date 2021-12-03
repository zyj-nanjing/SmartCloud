package www.bwsensing.com;

import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import www.bwsensing.com.common.netty.NettyServer;

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

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        ConfigurableApplicationContext configContext = SpringApplication.run(SmartCloudApplication.class, args);
        NettyServer nettyServer = configContext.getBean(NettyServer.class);
        nettyServer.run();
    }

}
