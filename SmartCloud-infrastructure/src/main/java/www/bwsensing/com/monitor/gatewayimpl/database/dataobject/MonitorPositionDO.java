package www.bwsensing.com.monitor.gatewayimpl.database.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
/**
 * @author macos-zyj
 */
@Data
public class MonitorPositionDO {
    /**主键*/
    private Integer id;
    /**结构物标识编码*/
    @JsonIgnore
    private Integer structureId;
    /**传感器编码*/
    private String sensorSn;
    /**位置名称**/
    private String name;
    /**图片*/
    private String picture;
    /**点位说明**/
    private String comment;
    /**排序**/
    private Integer orderSort;
    /***有效性*/
    private Boolean effective;
    /**绑定状态**/
    private Integer bindingStatus;
}
