package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.ProductDataItemCO;
import www.bwsensing.com.device.dto.command.ProductDataItemSaveCmd;
import www.bwsensing.com.device.dto.command.ProductDataItemUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductDataItemPageQuery;

/**
 * @author macos-zyj
 */
public interface ProductDataItemService {
    /**
     * 根据产品编号获取产品监测数据项
     * @param modelId
     * @return
     */
    MultiResponse<ProductDataItemCO> getProductDataItemsByModelId(Integer modelId);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<ProductDataItemCO> getProductDataItemsByPage(ProductDataItemPageQuery pageQuery);

    /**
     * 根据Id进行查询
     * @param id
     * @return
     */
    SingleResponse<ProductDataItemCO> getProductDataItemById(Integer id);

    /**
     * 添加产品检测项
     * @param saveCmd
     * @return
     */
    Response addProductDataItem(ProductDataItemSaveCmd saveCmd);


    /**
     * 修改产品检测项
     * @param updateCmd
     * @return
     */
    Response updateProductDataItem(ProductDataItemUpdateCmd updateCmd);


    /**
     * 根据Id删除产品检测项
     * @param id
     * @return
     */
    Response deleteProductDataItemById(Integer id);
}
