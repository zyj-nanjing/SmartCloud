package www.bwsensing.com.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件服务配置
 * @author macos-zyj
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.mail")
public class AliCloudMailProperties {
    private String regionId;
    private String host;
    private String version;
}
