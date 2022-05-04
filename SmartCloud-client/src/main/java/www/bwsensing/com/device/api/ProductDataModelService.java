package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelCO;
import www.bwsensing.com.device.dto.clientobject.ProductDataModelItemCO;
import www.bwsensing.com.device.dto.command.ProductDataModelItemAddCmd;
import www.bwsensing.com.device.dto.command.ProductDataModelItemUpdateCmd;
import www.bwsensing.com.device.dto.command.ProductDataModelAddCmd;
import www.bwsensing.com.device.dto.command.ProductDataModelUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductDataModelItemsPageQuery;
import www.bwsensing.com.device.dto.command.query.ProductDataModelsPageQuery;

/**
 * @author macos-zyj
 */
public interface ProductDataModelService {

    /**
     * 添加产品数据模型
     * @param addCmd
     * @return
     */
    Response addProductDateModel(ProductDataModelAddCmd addCmd);


    /**
     * 修改产品数据模型
     * @param updateCmd
     * @return
     */
    Response updateProductDateModel(ProductDataModelUpdateCmd updateCmd);

    /**
     * 删除对应的产品数据模型
     * @param modelId 数据模型编号
     * @return
     */
    Response deleteProductDateModel(Integer modelId);


    /**
     * 根据Id获取当前数据产品数据模型详细信息
     * @param id
     * @return
     */
    SingleResponse<ProductDataModelCO> getProductDataModelById(Integer id);

    /**
     * 关于数据模型的分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<ProductDataModelCO> getProductDataModelsByPageQuery(ProductDataModelsPageQuery pageQuery);

    /**
     * 新增数据项
     * @param addCmd
     * @return
     */
    Response addProductDataItem(ProductDataModelItemAddCmd addCmd);

    /**
     * 删除数据项
     * @param updateCmd
     * @return
     */
    Response updateProductDataItem(ProductDataModelItemUpdateCmd updateCmd);

    /**
     * 根据Id删除数据项
     * @param id
     * @return
     */
    Response deleteProductDataItemById(Integer id);

    /**
     * 产品数据数据项分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<ProductDataModelItemCO> getProductDataModelItemsByPageQuery(ProductDataModelItemsPageQuery pageQuery);

    /**
     * 根据Id获取数据项
     * @param id
     * @return
     */
    SingleResponse<ProductDataModelItemCO> getProductDataModelItemById(Integer id);
}
