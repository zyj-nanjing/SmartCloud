package www.bwsensing.com.stream.object;

import lombok.Data;
import java.sql.Timestamp;

/**
 * 带时间戳的设备信息
 * @author macos-zyj
 */
@Data
public class DataMessage {
    private Timestamp receiveTime;
    private String message;

    public DataMessage() {
    }

    public DataMessage(Timestamp receiveTime, String message) {
        this.receiveTime = receiveTime;
        this.message = message;
    }


}
