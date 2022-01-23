package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.export.SensorModelVO;
import www.bwsensing.com.dto.clientobject.ImportResultCO;
import www.bwsensing.com.dto.command.SensorModelSaveCmd;
import www.bwsensing.com.dto.command.SensorModelUpdateCmd;
import www.bwsensing.com.dto.clientobject.SensorModelCO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface SensorModelService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response saveModel( SensorModelSaveCmd saveCmd);


    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateModel( SensorModelUpdateCmd updateCmd);

    /**
     * 删除型号
     * @param modelId
     * @return
     */
    Response deleteModel(Integer modelId);
    /**
     * 查询所有模型
     * @return
     */
    MultiResponse<SensorModelCO> queryAllModels();

    /**
     * 查看详细模型
     * @param modelId
     * @return
     */
    SingleResponse<SensorModelCO> selectModelById(Integer modelId);

    /**
     * 数据导入处理
     * @param importCollection
     * @return
     */
    SingleResponse<ImportResultCO> importModels(List<SensorModelVO> importCollection);
}
