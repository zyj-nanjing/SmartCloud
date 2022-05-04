package www.bwsensing.com.device.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationModelDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ProductDataComputationModelMapper {
    /**
     * 查询产品数据计算模型
     *
     * @param id 产品数据计算模型主键
     * @return 产品数据计算模型
     */
    DataComputationModelDO getDataComputationModelById(Integer id);

    /**
     * 查询产品数据计算模型列表
     *
     * @param productDataComputationModel 产品数据计算模型
     * @return 产品数据计算模型集合
     */
    List<DataComputationModelDO> queryDataComputationModelBySort(DataComputationModelDO productDataComputationModel);


    /**
     * 删除计算模型与数据项关联
     * @param computationModelId
     * @return
     */
    int deleteDataComputationWithDataItem(Integer computationModelId);


    /**
     * 删除计算模型与产品额外配置关联
     * @param computationModelId
     * @return
     */
    int deleteDataComputationWithExtraDataItem(Integer computationModelId);

    /**
     * 保存计算模型与数据项关联
     * @param computationModelId
     * @param dataItemId
     * @return
     */
    int saveDataComputationWithDataItem(@Param("computationId") Integer computationModelId, @Param("dataItemId")Integer dataItemId);


    /**
     * 保存计算模型与产品配置关联
     * @param computationModelId
     * @param extraDataItemId
     * @return
     */
    int saveDataComputationWithExtraDataItem(@Param("computationId") Integer computationModelId,@Param("extraDataItemId") Integer extraDataItemId);

    /**
     * 新增产品数据计算模型
     *
     * @param dataComputationModel 产品数据计算模型
     * @return 结果
     */
    int saveDataComputationModel(DataComputationModelDO dataComputationModel);

    /**
     * 修改产品数据计算模型
     *
     * @param dataComputationModel 产品数据计算模型
     * @return 结果
     */
    int updateDataComputationModel(DataComputationModelDO dataComputationModel);

    /**
     * 删除产品数据计算模型
     *
     * @param id 产品数据计算模型主键
     * @return 结果
     */
    int deleteDataComputationModelById(Integer id);

    /**
     * 批量删除产品数据计算模型
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteDataComputationModelByIds(String[] ids);
}
