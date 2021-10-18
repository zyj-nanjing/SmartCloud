package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDataDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface SensorDataMapper {
    /**
     * 保存
     * @param sensorDate
     */
    void save(SensorDataDO sensorDate);

    /**
     * 修改
     * @param sensorDate
     */
    void update(SensorDataDO sensorDate);

    /**
     * 组合查询
     * @param sensorDate
     * @return
     */
    List<SensorDataDO> querySensorListBySort(SensorDataDO sensorDate);

    /**
     * 根据IP 获取最新一条 对应的sn 数据
     * @param ipAddress
     * @return
     */
    String selectSnByIpAddress(String ipAddress);
}
