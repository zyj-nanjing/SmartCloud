package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.ExtraProductDataItemCO;
import www.bwsensing.com.device.dto.command.ProductExtraDataItemSaveCmd;
import www.bwsensing.com.device.dto.command.ProductExtraDataItemUpdateCmd;
import www.bwsensing.com.device.dto.command.query.ProductExtraDataItemPageQuery;

/**
 * @author macos-zyj
 */
public interface ExtraProductDataItemService {
    /**
     * 根据产品编号获取产品监测数据项
     * @param modelId
     * @return
     */
    MultiResponse<ExtraProductDataItemCO> getProductExtraDataItemsByModelId(Integer modelId);

    /**
     * 分页查询
     * @param pageQuery
     * @return
     */
    PageResponse<ExtraProductDataItemCO> getProductExtraDataItemsByPage(ProductExtraDataItemPageQuery pageQuery);

    /**
     * 根据Id进行查询
     * @param id
     * @return
     */
    SingleResponse<ExtraProductDataItemCO> getProductExtraDataItemById(Integer id);

    /**
     * 添加产品检测项
     * @param saveCmd
     * @return
     */
    Response addProductDataItem(ProductExtraDataItemSaveCmd saveCmd);


    /**
     * 修改产品检测项
     * @param updateCmd
     * @return
     */
    Response updateExtraProductDataItem(ProductExtraDataItemUpdateCmd updateCmd);


    /**
     * 根据Id删除产品检测项
     * @param id
     * @return
     */
    Response deleteExtraProductDataItemById(Integer id);
}
