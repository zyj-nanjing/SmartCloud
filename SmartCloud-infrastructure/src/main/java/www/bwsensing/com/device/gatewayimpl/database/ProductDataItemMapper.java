package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import java.util.List;

/**
 * 监测项
 * @author macos-zyj
 */
public interface ProductDataItemMapper {

    /**
     * 保存
     * @param monitorItems
     */
    void saveDataItem(ProductDataItemDO monitorItems);

    /**
     * 修改
     * @param monitorItems
     */
    void updateDataItem(ProductDataItemDO monitorItems);

    /**
     * 删除
     * @param id
     */
    void deleteDataItemById(Integer id);

    /**
     * 删除
     * @param ids
     */
    void  deleteDataItemByIds(String ids);

    /**
     * 查询所有
     * @param queryObject
     * @return
     */
    List<ProductDataItemDO> queryProductDataItemBySort(ProductDataItemDO queryObject);

    /**
     * 根据Ids获取集合
     * @param ids
     * @return
     */
    List<ProductDataItemDO> queryProductDataItemByIds(List<Integer> ids);
    /**
     * 根据ID获取
     * @param id
     * @return
     */
    ProductDataItemDO getProductDataItemById(Integer id);

    /**
     * 获取传感器型号下的的所有item
     * @param modelId
     * @return
     */
    List<ProductDataItemDO> selectItemsByModelId(Integer modelId);

    /**
     * 根据传感器sn编码获取监测项信息
     * @param uniqueCode
     * @return
     */
    List<ProductDataItemDO> queryProductItemsByUniqueCode(String uniqueCode);

    /**
     * 根据检测项编码获取检测项
     * @param dataId
     * @return
     */
    ProductDataItemDO queryProductItemByDataId(String dataId);

}
