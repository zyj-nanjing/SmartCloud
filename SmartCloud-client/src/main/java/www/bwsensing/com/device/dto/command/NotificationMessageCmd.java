package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.extension.BizScenario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.cola.dto.Command;
import java.util.Date;
import lombok.Data;


/**
 * @author macos-zyj
 */
@Data
public class NotificationMessageCmd extends Command {
    private static final long serialVersionUID = 1292710012161732257L;
    /**
     * 预警组
     */
    private Integer alertGroupId;
    /**
     * 传感器名称
     */
    private String sensorName;
    private String sensorModel;
    private String currentProject;
    private String alertGroupName;
    private String sensorSn;
    private String alertName;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date alertTime;
    /**
     * 告警信息
     */
    private String alertMessage;
    private String color;
    /**用例场景*/
    private String scenario;
    private String bizId;
    private BizScenario bizScenario;

    public static class Mail{
        private String fileKey;
        private String fileLocation;

        public String getFileKey() {
            return fileKey;
        }

        public void setFileKey(String fileKey) {
            this.fileKey = fileKey;
        }

        public String getFileLocation() {
            return fileLocation;
        }

        public void setFileLocation(String fileLocation) {
            this.fileLocation = fileLocation;
        }
    }
}
