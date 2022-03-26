package www.bwsensing.com.domain.device.gateway;

import java.util.List;
import www.bwsensing.com.domain.device.model.SensorModel;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;

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

    /**
     * 根据绑定的网络模型编号获取对应的数据模型集合
     * @param webModelId
     * @return
     */
    List<ProductDataModel> getDataModelByWebId(Integer webModelId);
}
