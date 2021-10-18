package www.bwsensing.com.common.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 时序数据库告警及规则配置
 * @author macos-zyj
 */
@Slf4j
@Data
@Component
public class AlertConfig {
    @Value("${system.alert.prefix}")
    private String prefix;
    @Value("${system.alert.server-host}")
    private String alertServerHost;
    @Value("${system.alert.port}")
    private Integer alertServerPort;

    public String getServerHost(){
        return this.prefix+this.alertServerHost+":"+alertServerPort;
    }
}
