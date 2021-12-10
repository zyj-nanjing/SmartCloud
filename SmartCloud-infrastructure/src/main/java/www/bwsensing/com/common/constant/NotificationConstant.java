package www.bwsensing.com.common.constant;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * @author macos-zyj
 */
@Component
public class NotificationConstant implements InitializingBean {
    public static Integer  MAX_LAST;
    public static String PREFIX;
    public static final TimeUnit TIME_UNIT = TimeUnit.DAYS;

    @Value("${system.notification.max_last}")
    private String lasting;
    @Value("${system.notification.prefix}")
    private String prefix;

    @Override
    public void afterPropertiesSet() {
        NotificationConstant.MAX_LAST = Integer.parseInt(this.lasting);
        NotificationConstant.PREFIX = this.prefix;
    }
}
