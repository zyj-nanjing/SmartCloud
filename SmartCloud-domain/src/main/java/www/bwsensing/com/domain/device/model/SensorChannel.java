package www.bwsensing.com.domain.device.model;

import lombok.Data;

/**
 * 设备通道
 * @author macos-zyj
 */
@Data
public class SensorChannel {
    private String ipAddress;
    private String channelId;
    private String snCode;

    public SensorChannel() {
    }

    public SensorChannel(String ipAddress, String channelId, String snCode) {
        this.ipAddress = ipAddress;
        this.channelId = channelId;
        this.snCode = snCode;
    }
}
