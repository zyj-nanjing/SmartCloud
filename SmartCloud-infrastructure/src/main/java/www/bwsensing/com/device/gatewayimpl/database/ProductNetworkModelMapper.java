package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductNetworkModelDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ProductNetworkModelMapper {
    /**
     * 查询产品网络模型
     *
     * @param id 产品网络模型主键
     * @return 产品网络模型
     */
    ProductNetworkModelDO getNetworkModelById(Integer id);

    /**
     * 查询产品网络模型列表
     *
     * @param productNetworkModelDO 产品网络模型
     * @return 产品网络模型集合
     */
    List<ProductNetworkModelDO> queryNetworkModelBySort(ProductNetworkModelDO productNetworkModelDO);

    /**
     * 新增产品网络模型
     *
     * @param productNetworkModelDO 产品网络模型
     * @return 结果
     */
    int saveNetworkModel(ProductNetworkModelDO productNetworkModelDO);

    /**
     * 修改产品网络模型
     *
     * @param productNetworkModelDO 产品网络模型
     * @return 结果
     */
    int updateNetworkModel(ProductNetworkModelDO productNetworkModelDO);

    /**
     * 删除产品网络模型
     *
     * @param id 产品网络模型主键
     * @return 结果
     */
    int deleteNetworkModelById(Integer id);

    /**
     * 批量删除产品网络模型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteNetworkModelByIds(String[] ids);
}
