package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.SensorHistoryDO;

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
