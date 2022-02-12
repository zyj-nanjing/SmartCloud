package www.bwsensing.com.gatewayimpl.database.dataobject;

import lombok.Data;
import java.util.Date;
/**
 * @author macos-zyj
 */
@Data
public class BizScheduledConfig {
    /** 主键 */
    private Integer id;

    /** 事务编码 */
    private String scheduleCode;

    /** 事务名称 */
    private String scheduledName;

    /** 检查间隔 */
    private Integer checkInterval;

    /** 业务线编码 */
    private String bizId;

    /** 当前权级 */
    private Double weight;

    /** 偏移权级 */
    private Double shiftWeight;

    private Boolean isHealthy;

    /** 备注 */
    private String remark;

    /** 创建人 */
    private String creator;

    /** 创建时间 */
    private Date createTime;
}
