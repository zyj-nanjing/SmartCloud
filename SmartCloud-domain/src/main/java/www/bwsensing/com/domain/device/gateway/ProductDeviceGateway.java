package www.bwsensing.com.domain.device.gateway;

import www.bwsensing.com.domain.device.model.DeviceComputation;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.ProductDevice;
import java.util.List;

/**
 * 传感器领域网关
 * @author macos-zyj
 */
public interface ProductDeviceGateway {
    /**
     * 保存
     * @param device
     */
    void saveProductDevice(ProductDevice device);

    /**
     * 修改
     * @param device
     */
    void updateProductDevice(ProductDevice device);

    /**
     * 修改列表
     * @param devices
     */
    void updateProductDevices(List<ProductDevice> devices);

    /**
     * 删除传感器
     * @param id
     */
    void deleteProductDeviceById(Integer id);


    /**
     * 根据结构物编号获取传感器集合
     * @param structureId
     * @return
     */
    List<ProductDevice> getSensorsByMonitorStructure(Integer structureId);

    /**
     * 获取传感器型号并校验权限
     * @param sensorId
     * @return
     */
    ProductDevice getDeviceDetailById(Integer sensorId);

    /**
     * 新增计算模型与设备关联
     * @param deviceComputation
     */
    void addDeviceComputation(DeviceComputation deviceComputation);

    /**
     * 修改计算模型与设备关联
     * @param deviceComputation
     */
    void updateDeviceComputation(DeviceComputation deviceComputation);

    /**
     * 删除计算模型与设备关联
     * @param id
     */
    void deleteDeviceComputation(Integer id);

    /**
     * 根据设备唯一编码查询关联的对应数据项
     * @param uniqueId
     * @return
     */
    List<ProductDataItem> getProductDataItemByUniqueId(Integer uniqueId);
}
