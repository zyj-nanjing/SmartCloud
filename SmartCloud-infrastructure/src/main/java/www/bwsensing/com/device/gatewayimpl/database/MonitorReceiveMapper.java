package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.MonitorReceiveDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface MonitorReceiveMapper {
    /**
     * 条件查询
     * @param querySort
     * @return
     */
    List<MonitorReceiveDO> selectMonitorReceiveBySort(MonitorReceiveDO querySort);

    /**
     * 根据Id 获取 接收日志
     * @param id
     * @return
     */
    MonitorReceiveDO getMonitorReceiveById(Integer id);

    /**
     * 保存接收消息
     * @param dataObject
     */
    void saveReceive(MonitorReceiveDO dataObject);

    /**
     * 根据ID删除
     * @param id
     */
    void deleteReceiveById(Integer id);

    /**
     * 根据ID进行删除
     * @param ids
     */
    void deleteReceiveByIds(String ids);
}
