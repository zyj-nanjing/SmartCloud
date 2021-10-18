package www.bwsensing.com.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author macos-zyj
 */
@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud")
public class AliCloudProperties {
    public static final String PROPERTY_PREFIX = "alibaba.cloud";
    public static final String ACCESS_KEY_PROPERTY = "alibaba.cloud.access-key";
    public static final String SECRET_KEY_PROPERTY = "alibaba.cloud.secret-key";
    private String accessKey;
    private String secretKey;
}
