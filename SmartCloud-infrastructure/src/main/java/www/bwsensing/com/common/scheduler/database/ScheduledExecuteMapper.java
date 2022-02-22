package www.bwsensing.com.common.scheduler.database;

import www.bwsensing.com.common.scheduler.database.dataobject.ScheduledExecute;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ScheduledExecuteMapper {

    /**
     * 保存事务执行结果
     * @param scheduledExecute
     */
    void saveScheduledExecute(ScheduledExecute scheduledExecute);

    /**
     * 修改执行结果
     * @param scheduledExecute
     */
    void updateScheduledExecute(ScheduledExecute scheduledExecute);

    /**
     * 查询事务执行
     * @param scheduledExecute
     * @return
     */
    List<ScheduledExecute> queryScheduledExecute(ScheduledExecute scheduledExecute);

}
