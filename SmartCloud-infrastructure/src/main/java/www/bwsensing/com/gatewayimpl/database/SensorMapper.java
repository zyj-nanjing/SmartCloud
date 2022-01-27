package www.bwsensing.com.gatewayimpl.database;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SensorMapper {
    /**
     * 保存
     * @param saveObject
     * @return
     */
    int  save(SensorDO saveObject);

    /**
     * 更新
     * @param updateObject
     * @return
     */
    int update(SensorDO updateObject);

    /**
     * 根据Id 删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 校验测点数据数量
     * @param positionId
     * @return
     */
    int countByPositionId(Integer positionId);

    /**
     *项目绑定
     * @param id
     * @param projectId
     */
    void bindProject(@Param("sid")Integer id,@Param("pid")Integer projectId);

    /**
     * 删除对应关联
     * @param projectId
     */
    void deleteSensorBind(Integer projectId);

    /***
     * 绑定解绑传感器
     * @param bindSensor
     */
    void bindSensorMethod(SensorDO bindSensor);
    /**
     * 组合查询
     * @param saveObject
     * @return
     */
    List<SensorDO> selectSensorBySort(SensorDO saveObject);

    /**
     * 获取
     * @param groupId
     * @return
     */
    List<SensorDO> selectSensorByGroupId(Integer groupId);

    /**
     * 获取
     * @param projectId
     * @return
     */
    List<SensorDO> selectSensorByProjectId(Integer projectId);

    /**
     * 根据结构物编号获取传感器集合
     * @param structureId
     * @return
     */
    List<SensorDO> getSensorsByMonitorStructure(Integer structureId);

    /**
     * 查询传感绑定情况
     * @param projectId
     * @param groupId
     * @return
     */
    List<SensorDO> selectSensorBindByProject(@Param("pid")Integer projectId, @Param("gid")Integer groupId);

    /**
     * 根据测点进行查询
     * @param positionId
     * @param groupId
     * @return
     */
    List<SensorDO> selectSensorBindByPosition(@Param("pid")Integer positionId, @Param("gid")Integer groupId);

    /**
     * 根据产品型号进行查询
     * @param modelId
     * @return
     */
    List<SensorDO> selectSensorByModelId(Integer modelId);
    /***
     * 根据设备Sn 码获取信息
     * @param sn
     * @return
     */
    SensorDO selectSensorBySn(String sn);

    /**
     * 根据Id 进行查询
     * @param id
     * @return
     */
    SensorDO selectSensorById(Integer id);

    /**
     * 根据测点查询传感器
     * @param positionId
     * @return
     */
    SensorDO selectSensorByPosition(Integer positionId);

    /**
     * 根据
     * @param sensorSn
     * @return
     */
    String selectModelBySensorSn(String sensorSn);

    /**
     * 根据Sn 查询对应的解析算法
     * @param sensorSn
     * @return
     */
    String queryAnalyseFunctionBySn(String sensorSn);
}
