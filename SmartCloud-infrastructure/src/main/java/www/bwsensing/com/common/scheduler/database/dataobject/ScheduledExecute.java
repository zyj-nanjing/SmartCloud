package www.bwsensing.com.common.scheduler.database.dataobject;

import lombok.Data;
import java.util.Date;

/**
 * 事务执行日志
 * @author macos-zyj
 */
@Data
public class ScheduledExecute {

    /**
     * 主键
     */
    private Integer id;

    /**
     * 事务编号
     */
    private Integer scheduleId;

    /**
     * 服务编号
     */
    private Integer serviceId;

    /**
     * 检查时间
     */
    private Date checkTime;

    /**
     * 是否结束
     */
    private Boolean isEnd;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 查询层级
     */
    private Integer checkInterval;

    public ScheduledExecute() {
    }

    public ScheduledExecute(Integer scheduleId, Integer checkInterval) {
        this.scheduleId = scheduleId;
        this.checkInterval = checkInterval;
    }
}
