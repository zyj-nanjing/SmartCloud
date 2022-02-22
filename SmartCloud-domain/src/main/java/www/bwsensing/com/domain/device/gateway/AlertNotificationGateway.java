package www.bwsensing.com.domain.device.gateway;

import www.bwsensing.com.domain.device.model.alert.AlertNotification;

/**
 * 预警通知领域网关
 * @author macos-zyj
 */
public interface AlertNotificationGateway {

    /**
     * 接收并保存日志
     * @param alertNotification
     */
    void receiveNotification(AlertNotification alertNotification);
}
