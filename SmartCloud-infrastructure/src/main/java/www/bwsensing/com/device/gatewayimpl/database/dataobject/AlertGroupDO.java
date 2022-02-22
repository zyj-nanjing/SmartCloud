package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * @author macos-zyj
 */
@Data
public class AlertGroupDO {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer id;

    /** 预警分组名称 */
    private String groupName;

    /** 操作组 */
    private Integer operateGroupId;

    /** 当前传感器 */
    private Integer currentSensorId;

    /** 模板编号 */
    private Integer templateId;

    /**推送方式*/
    private Integer pushType;

    /** 创建者 */
    private String creator;

    /**创建时间*/
    private Date createTime;

}
