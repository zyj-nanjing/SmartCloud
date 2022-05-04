package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.DataComputationModelCO;
import www.bwsensing.com.device.dto.command.DataComputationModelSaveCmd;
import www.bwsensing.com.device.dto.command.DataComputationModelUpdateCmd;
import www.bwsensing.com.device.dto.command.query.DataComputationModelPageQuery;

/**
 * 计算模型
 * @author macos-zyj
 */
public interface DataComputationModelService {

    /**
     * 添加计算模型
     * @param saveCmd
     * @return
     */
    Response addDataComputationModel(DataComputationModelSaveCmd saveCmd);


    /**
     * 修改计算模型
     * @param updateCmd
     * @return
     */
    Response updateDataComputationModel(DataComputationModelUpdateCmd updateCmd);


    /**
     * 根据Id 删除计算模型
     * @param id
     * @return
     */
    Response deleteDataComputationModel(Integer id);

    /**
     * 根据Id获取对应的计算模型
     * @param id
     * @return
     */
    SingleResponse<DataComputationModelCO> getDataComputationModelById(Integer id);

    /**
     * 数据分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<DataComputationModelCO> getDataComputationModelBySort(DataComputationModelPageQuery pageQuery);

    /**
     * 根据
     * @param modelId
     * @return
     */
    MultiResponse<DataComputationModelCO> getDataComputationModelByModelId(Integer modelId);
}
