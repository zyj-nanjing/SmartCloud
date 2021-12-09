package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class AlertNotificationDO {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 预警分组编号
     */
    private Integer alertGroupId;
    /**
     * 业务组编号
     */
    private Integer groupId;
    /**
     * 业务组名称
     */
    private String groupName;
    /**
     * 告警名称
     */
    private String alertName;
    /**
     * 规则名称
     */
    private String roleName;
    /**
     * 传感器编号
     */
    private Integer sensorId;
    /**
     * 传感器Sn编码
     */
    private String sn;
    /**
     * 传感器名称
     */
    private String sensorName;
    /**
     * 传感器型号
     */
    private String sensorModel;
    /**
     * 传感器型号编号
     */
    private Integer modelId;
    /**
     * 告警时间
     */
    private Date alertTime;
    /**
     * 是否被处理
     */
    private Boolean isHandle;
    /**
     * 告警信息
     */
    private String summary;
    /**
     * 提示颜色
     */
    private String color;
}
