package www.bwsensing.com.domainevent;

import com.fasterxml.jackson.annotation.JsonFormat;
import www.bwsensing.com.event.DomainEventI;
import java.util.Date;
import lombok.Data;


/**
 * @author macos-zyj
 */
@Data
public class AlertNotificationPushEvent implements DomainEventI {
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
    /**
     * 推送方式
     */
    private Integer pushMethod;

}
