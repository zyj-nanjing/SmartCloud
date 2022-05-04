package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.ProductNetworkModelCO;
import www.bwsensing.com.device.dto.command.ProductNetworkModelSaveCmd;
import www.bwsensing.com.device.dto.command.ProductNetworkModelUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductNetworkModelPageQuery;

/**
 * @author macos-zyj
 */
public interface ProductNetworkModelService {

    /**
     * 添加产品型号网络模型
     * @param saveCmd
     * @return
     */
    Response addNetworkModel(ProductNetworkModelSaveCmd saveCmd);


    /**
     * 修改产品型号网络模型
     * @param updateCmd
     * @return
     */
    Response updateNetworkModel(ProductNetworkModelUpdateCmd updateCmd);

    /**
     * 根据编号删除网络模型
     * @param id
     * @return
     */
    Response deleteNetworkModelById(Integer id);


    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<ProductNetworkModelCO> queryProductModelNetworkBySort(ProductNetworkModelPageQuery pageQuery);

    /**
     * 根据Id获取
     * @param id
     * @return
     */
    SingleResponse<ProductNetworkModelCO> getNetworkModelById(Integer id);
}
