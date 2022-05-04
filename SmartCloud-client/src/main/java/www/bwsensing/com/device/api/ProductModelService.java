package www.bwsensing.com.device.api;

import java.util.List;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.export.ProductModelVO;
import www.bwsensing.com.common.clientobject.ImportResultCO;
import www.bwsensing.com.device.dto.clientobject.ProductKindCO;
import www.bwsensing.com.device.dto.clientobject.ProductModelCO;
import www.bwsensing.com.device.dto.command.ProductModelSaveCmd;
import www.bwsensing.com.device.dto.command.ProductModelUpdateCmd;


/**
 * @author macos-zyj
 */
public interface ProductModelService {
    /**
     * 保存
     * @param saveCmd
     * @return
     */
    Response saveModel( ProductModelSaveCmd saveCmd);


    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateModel( ProductModelUpdateCmd updateCmd);

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
    MultiResponse<ProductModelCO> queryProductModels();

    /**
     * 获取产品型号类型
     * @return
     */
    MultiResponse<ProductKindCO> getProductModelKinds();

    /**
     * 查看详细模型
     * @param modelId
     * @return
     */
    SingleResponse<ProductModelCO> selectModelById(Integer modelId);

    /**
     * 数据导入处理
     * @param importCollection
     * @return
     */
    SingleResponse<ImportResultCO> importModels(List<ProductModelVO> importCollection);
}
