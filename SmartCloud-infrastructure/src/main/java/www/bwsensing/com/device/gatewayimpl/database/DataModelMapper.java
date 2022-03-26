package www.bwsensing.com.device.gatewayimpl.database;

import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataModelDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface DataModelMapper {
    /**
     * 查询接入设备数据模型
     *
     * @param id 接入设备数据模型主键
     * @return 接入设备数据模型
     */
    DataModelDO queryDataModelById(Integer id);

    /**
     * 查询接入设备数据模型列表
     *
     * @param dataModel 接入设备数据模型
     * @return 接入设备数据模型集合
     */
    List<DataModelDO> queryDataModelBySort(DataModelDO dataModel);

    /**
     * 新增接入设备数据模型
     *
     * @param dataModel 接入设备数据模型
     * @return 结果
     */
    int saveDataModel(DataModelDO dataModel);

    /**
     * 修改接入设备数据模型
     *
     * @param dataModel 接入设备数据模型
     * @return 结果
     */
    int updateDataModel(DataModelDO dataModel);

    /**
     * 删除接入设备数据模型
     *
     * @param id 接入设备数据模型主键
     * @return 结果
     */
    int deleteDataModelById(Integer id);

    /**
     * 批量删除接入设备数据模型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDataModelByIds(String[] ids);

    /**
     * 根据网络配置获取对应的数据模型
     * @param webId
     * @return
     */
    List<DataModelDO> getDataModelByWebId(Integer webId);
}
