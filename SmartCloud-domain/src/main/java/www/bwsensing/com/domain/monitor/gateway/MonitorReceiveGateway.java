package www.bwsensing.com.domain.monitor.gateway;


import www.bwsensing.com.domain.device.model.data.MonitorReceive;

/**
 * @author macos-zyj
 */
public interface MonitorReceiveGateway {
    /**
     * 存储传感器数据
     * @param receive
     */
    void storageMonitorReceive(MonitorReceive receive,Boolean isDirect);
}
