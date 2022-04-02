package www.bwsensing.com.domain.device.gateway;

import www.bwsensing.com.domain.device.model.SensorInfo;
import www.bwsensing.com.domain.monitor.model.MonitorItem;

import java.util.List;

/**
 * 传感器领域网关
 * @author macos-zyj
 */
public interface SensorGateway {
    /**
     * 保存
     * @param sensorInfo
     */
    void saveSensor(SensorInfo sensorInfo);

    /**
     * 修改
     * @param sensorInfo
     */
    void updateSensor(SensorInfo sensorInfo);

    /**
     * 修改列表
     * @param sensors
     */
    void updateSensors(List<SensorInfo> sensors);
    /**
     * 删除传感器
     * @param id
     */
    void deleteSensor(Integer id);

    /**
     * 获取当前分组的传感器
     * @return
     */
    List<SensorInfo> selectSensorByCurrentGroup();

    /**
     * 根据结构物编号获取传感器集合
     * @param structureId
     * @return
     */
    List<SensorInfo> getSensorsByMonitorStructure(Integer structureId);

    /**
     * 获取传感器型号并校验权限
     * @param sensorId
     * @return
     */
    SensorInfo getSensorInfoById(Integer sensorId);

    /**
     * 根据设备唯一编码查询关联的对应数据项
     * @param uniqueId
     * @return
     */
    List<MonitorItem> getMonitorItemByUniqueId(Integer uniqueId);
}
