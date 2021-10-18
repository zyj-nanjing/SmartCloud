package www.bwsensing.com.domain.device.alert;

import lombok.Data;

/**
 * 消息通知配置
 * @author macos-zyj
 */
@Data
public class NotificationConfig {
    /**主键编号*/
    private Integer id;
    /**
     * 消息推送方式
     */
    private NotificationMethod notificationMethod;
}
