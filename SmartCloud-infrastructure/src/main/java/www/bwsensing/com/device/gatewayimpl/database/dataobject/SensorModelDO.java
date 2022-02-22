package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorPrototypeDO;

import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Data
public class SensorModelDO {
    /**主键**/
    private Integer id;
    /**模型名称*/
    private String modelName;
    /**模型类型**/
    private String modelKind;
    /**型号图片*/
    private String picture;
    /**说明**/
    private String comment;
    /**监控类型*/
    private List<MonitorPrototypeDO> prototypeList;
    /**版本*/
    private Double version;
    /**创建时间*/
    private Date createTime;
    /**是否有效**/
    private Boolean isEffective;
}
