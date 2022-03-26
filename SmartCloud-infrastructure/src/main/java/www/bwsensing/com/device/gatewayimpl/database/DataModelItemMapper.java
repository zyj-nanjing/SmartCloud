package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelItemDO;
import java.util.List;

/**
 * 接入设备数据模型数据项Mapper接口
 *
 * @author ruoyi
 * @date 2022-03-18
 */
public interface DataModelItemMapper
{
    /**
     * 查询接入设备数据模型数据项
     *
     * @param id 接入设备数据模型数据项主键
     * @return 接入设备数据模型数据项
     */
    DataModelItemDO queryDataModelItemById(Integer id);

    /**
     * 查询接入设备数据模型数据项列表
     *
     * @param productDataModelItem 接入设备数据模型数据项
     * @return 接入设备数据模型数据项集合
     */
    List<DataModelItemDO> queryDataModelItemBySort(DataModelItemDO productDataModelItem);

    /**
     * 新增接入设备数据模型数据项
     *
     * @param dataModelItem 接入设备数据模型数据项
     * @return 结果
     */
    int saveDataModelItem(DataModelItemDO dataModelItem);

    /**
     * 修改接入设备数据模型数据项
     *
     * @param dataModelItem 接入设备数据模型数据项
     * @return 结果
     */
    int updateDataModelItem(DataModelItemDO dataModelItem);

    /**
     * 删除接入设备数据模型数据项
     *
     * @param id 接入设备数据模型数据项主键
     * @return 结果
     */
    int deleteDataModelItemById(Integer id);

    /**
     * 批量删除接入设备数据模型数据项
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDataModelItemByIds(String[] ids);
}