package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ProductDeviceMapper {
    /**
     * 保存
     * @param saveObject
     * @return
     */
    int saveProductDevice(ProductDeviceDO saveObject);

    /**
     * 更新
     * @param updateObject
     * @return
     */
    int updateProductDevice(ProductDeviceDO updateObject);

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
    void deleteProductBind(Integer projectId);

    /***
     * 绑定解绑传感器
     * @param bindSensor
     */
    void bindDeviceWithPosition(ProductDeviceDO bindSensor);

    /**
     * 保存设备额外信息
     * @param deviceId 设备Id
     * @param dataIemId 额外数据项Id
     * @param dataValue 数据值
     */
    void saveDeviceExtraData(@Param("deviceId")Integer deviceId,@Param("dataIemId")Integer dataIemId,@Param("dataValue")String dataValue);


    /**
     * 组合查询
     * @param saveObject
     * @return
     */
    List<ProductDeviceDO> selectProductDeviceBySort(ProductDeviceDO saveObject);

    /**
     * 获取
     * @param groupId
     * @return
     */
    List<ProductDeviceDO> selectSensorByGroupId(Integer groupId);

    /**
     * 获取
     * @param projectId
     * @return
     */
    List<ProductDeviceDO> selectSensorByProjectId(Integer projectId);

    /**
     * 根据结构物编号获取传感器集合
     * @param structureId
     * @return
     */
    List<ProductDeviceDO> getSensorsByMonitorStructure(Integer structureId);

    /**
     * 根据测点进行查询
     * @param positionId
     * @param groupId
     * @return
     */
    List<ProductDeviceDO> selectSensorBindByPosition(@Param("pid")Integer positionId, @Param("gid")Integer groupId);

    /**
     * 根据产品型号进行查询
     * @param modelId
     * @return
     */
    List<ProductDeviceDO> selectSensorByModelId(Integer modelId);
    /***
     * 根据设备unitCode获取信息
     * @param unitCode
     * @return
     */
    ProductDeviceDO getProductDetailByUniqueCode(String unitCode);

    /**
     * 根据Id 进行查询
     * @param id
     * @return
     */
    ProductDeviceDO getProductDetailById(Integer id);

    /**
     * 根据测点查询传感器
     * @param positionId
     * @return
     */
    ProductDeviceDO selectProductsByPosition(Integer positionId);

}
