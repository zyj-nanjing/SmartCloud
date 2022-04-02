package www.bwsensing.com.device.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.device.dto.command.NotificationMessageCmd;

/**
 * 消息通知扩展点
 * @author macos-zyj
 */
public interface AlertNotificationExtPt extends ExtensionPointI {
    /**
     * 单条推送
     * @param message
     * @param userId
     */
    void singleNotification(NotificationMessageCmd message, Integer userId);

    /**
     * 批量推送
     * @param message
     */
    void singleNotification(NotificationMessageCmd message) ;
    /**
     * 批量阿里云模板发送
     * @param message
     */
    void batchNotification(NotificationMessageCmd message);
}
