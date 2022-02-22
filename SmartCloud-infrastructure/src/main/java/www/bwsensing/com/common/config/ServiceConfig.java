package www.bwsensing.com.common.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author macos-zyj
 */
@Data
@Component
public class ServiceConfig implements InitializingBean {

    public static Long WEIGHT;

    public static String LOCATION;

    public static String REMARK;

    public static String CONFIGURATION;

    /**权重*/
    @Value("${server.weight}")
    private  String weight;

    /**部署区域*/
    @Value("${server.location}")
    private  String location;

    /**备注*/
    @Value("${server.remark}")
    private  String remark;

    /**配置*/
    @Value("${server.configure}")
    private String configure;


    @Override
    public void afterPropertiesSet() {
        ServiceConfig.WEIGHT = Long.parseLong(this.weight);
        ServiceConfig.LOCATION = location;
        ServiceConfig.REMARK = remark;
        ServiceConfig.CONFIGURATION = configure;
    }
}
