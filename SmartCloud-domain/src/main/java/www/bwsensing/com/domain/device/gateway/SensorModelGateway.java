package www.bwsensing.com.domain.device.gateway;

import www.bwsensing.com.domain.device.model.SensorModel;

/**
 * 传感器模板
 * @author macos-zyj
 */
public interface SensorModelGateway {
    /**
     * 保存
     * @param saveModel
     */
    void saveModel(SensorModel saveModel);

    /**
     * 修改
     * @param updateModel
     */
    void updateModel(SensorModel updateModel);

    /**
     * 删除
     * @param modelId
     */
    void deleteModel(Integer modelId);
}
