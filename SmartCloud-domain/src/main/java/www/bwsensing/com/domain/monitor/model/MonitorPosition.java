package www.bwsensing.com.domain.monitor.model;

import lombok.Data;
import www.bwsensing.com.domain.device.model.SensorInfo;

/**
 * @author macos-zyj
 */
@Data
public class MonitorPosition {
    /**主键*/
    private Integer id;
    /**结构物标识编码*/
    private Integer structureId;
    /**位置名称**/
    private String name;
    /**点位说明**/
    private String comment;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**绑定的传感器*/
    private SensorInfo equipment;
    /**绑定状态**/
    private PositionStatus bindingStatus;

}
