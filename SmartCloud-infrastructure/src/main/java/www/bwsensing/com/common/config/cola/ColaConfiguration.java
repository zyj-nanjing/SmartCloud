package www.bwsensing.com.common.config.cola;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import www.bwsensing.com.common.boot.SpringBootstrap;

/**
 * @author macos-zyj
 */
@Configuration
@ComponentScan(value = {"com.alibaba.cola","www.bwsensing.com"})
public class ColaConfiguration {
    @Bean(initMethod = "init")
    public SpringBootstrap bootstrap() {
        return new SpringBootstrap();
    }
}
