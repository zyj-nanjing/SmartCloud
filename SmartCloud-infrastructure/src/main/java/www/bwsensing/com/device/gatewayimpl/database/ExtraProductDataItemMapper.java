package www.bwsensing.com.device.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ExtraProductDataItemDO;
import java.util.List;

/**
 * 产品附加固定数值项Mapper接口
 *
 * @author ruoyi
 * @date 2022-04-24
 */
public interface ExtraProductDataItemMapper
{
    /**
     * 查询产品附加固定数值项
     *
     * @param id 产品附加固定数值项主键
     * @return 产品附加固定数值项
     */
    ExtraProductDataItemDO getExtraProductDataItemById(Integer id);

    /**
     * 查询产品附加固定数值项列表
     *
     * @param extraProductDataItem 产品附加固定数值项
     * @return 产品附加固定数值项集合
     */
    List<ExtraProductDataItemDO> queryExtraProductDataItemBySort(ExtraProductDataItemDO extraProductDataItem);

    /**
     * 根据产品型号获取所有的额外数据数据值
     * @param modelId
     * @return
     */
    List<ExtraProductDataItemDO> getExtraDataItemDataByModelId(Integer modelId);

    /**
     * 根据设备编号获取所有的额外数据数据值
     * @param deviceId
     * @return
     */
    List<ExtraProductDataItemDO> getExtraDataItemDataByDeviceId(Integer deviceId);

    /**
     * 根据Id集合获取额外产品信息
     * @param ids
     * @return
     */
    List<ExtraProductDataItemDO> getExtraDataItemDataByIds(List<Integer> ids);

    /**
     * 新增产品附加固定数值项
     *
     * @param extraProductDataItem 产品附加固定数值项
     * @return 结果
     */
    int saveExtraProductDataItem(ExtraProductDataItemDO extraProductDataItem);

    /**
     * 修改产品附加固定数值项
     *
     * @param extraProductDataItem 产品附加固定数值项
     * @return 结果
     */
    int updateExtraProductDataItem(ExtraProductDataItemDO extraProductDataItem);

    /**
     * 删除产品附加固定数值项
     *
     * @param id 产品附加固定数值项主键
     * @return 结果
     */
    int deleteExtraProductDataItemById(Integer id);

    /**
     * 根据设备编号删除所有的
     * @param deviceId
     * @return
     */
    int deleteExtraProductItemDataByDeviceId(Integer deviceId);

    /**
     * 批量删除产品附加固定数值项
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteExtraProductDataItemByIds(String[] ids);
}
