package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorHistoryDO;

/**
 * @author macos-zyj
 */
public interface SensorHistoryMapper {
    /**
     * 保存
     * @param sensorHistory
     */
    void save(SensorHistoryDO sensorHistory);

    /***
     * 删除
     * @param id
     */
    void delete(Integer id);
}
