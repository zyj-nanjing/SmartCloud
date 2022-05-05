package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.Response;
import www.bwsensing.com.device.dto.command.DeviceComputationSaveCmd;
import www.bwsensing.com.device.dto.command.DeviceComputationUpdateCmd;

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
}
