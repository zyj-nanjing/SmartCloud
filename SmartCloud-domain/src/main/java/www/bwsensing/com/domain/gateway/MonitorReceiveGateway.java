package www.bwsensing.com.domain.gateway;


import www.bwsensing.com.domain.device.data.MonitorReceive;

/**
 * @author macos-zyj
 */
public interface MonitorReceiveGateway {
    /**
     * 存储传感器数据
     * @param receive
     */
    void storageMonitorReceive(MonitorReceive receive);
}
