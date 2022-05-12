package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.DeviceComputationCO;
import www.bwsensing.com.device.dto.command.DeviceComputationSaveCmd;
import www.bwsensing.com.device.dto.command.DeviceComputationUpdateCmd;
import www.bwsensing.com.device.dto.command.query.DeviceComputationPageQuery;


/**
 * 设备与计算模型关联服务
 * @author macos-zyj
 */
public interface DeviceComputationService {
    /**
     * 添加计算模型与设备管理
     * @param saveCmd
     * @return
     */
    Response addDeviceComputation(DeviceComputationSaveCmd saveCmd);

    /**
     *修改设备与计算模型关联
     * @param updateCmd
     * @return
     */
    Response updateDeviceComputation(DeviceComputationUpdateCmd updateCmd);

    /**
     * 根据编号删除对应的计算模型与设备的关联
     * @param id
     * @return
     */
    Response deleteDeviceComputation(Integer id);

    /**
     * 分页查询设备计算模型关联
     * @param pageQuery
     * @return
     */
    PageResponse<DeviceComputationCO> getDeviceComputationBySort(DeviceComputationPageQuery pageQuery);

    /**
     * 根据Id获取对应的设备计算模型关联信息
     * @param id
     * @return
     */
    SingleResponse<DeviceComputationCO> getDeviceComputationById(Integer id);
}
