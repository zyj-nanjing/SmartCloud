package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface DeviceComputationMapper {
    /**
     * 查询设备与计算模型关联
     *
     * @param id 设备与计算模型关联主键
     * @return 设备与计算模型关联
     */
    DeviceComputationDO getDeviceComputationById(Integer id);

    /**
     * 查询设备与计算模型关联列表
     *
     * @param deviceComputation 设备与计算模型关联
     * @return 设备与计算模型关联集合
     */
    List<DeviceComputationDO> queryDeviceComputationBySort(DeviceComputationDO deviceComputation);

    /**
     * 新增设备与计算模型关联
     *
     * @param deviceComputation 设备与计算模型关联
     * @return 结果
     */
    int insertDeviceComputation(DeviceComputationDO deviceComputation);

    /**
     * 修改设备与计算模型关联
     *
     * @param deviceComputation 设备与计算模型关联
     * @return 结果
     */
    int updateDeviceComputation(DeviceComputationDO deviceComputation);

    /**
     * 删除设备与计算模型关联
     *
     * @param id 设备与计算模型关联主键
     * @return 结果
     */
    int deleteDeviceComputationById(Integer id);

    /**
     * 批量删除设备与计算模型关联
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDeviceComputationByIds(String[] ids);
}
