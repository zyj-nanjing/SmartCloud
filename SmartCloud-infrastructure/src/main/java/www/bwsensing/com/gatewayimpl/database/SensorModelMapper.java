package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorModelDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface SensorModelMapper {
    /***
     * 保存
     * @param saveModel
     */
    void saveModel(SensorModelDO saveModel);

    /**
     * 保存类型关联
     * @param modelId
     * @param typeId
     */
    void saveModelLink(@Param("mid")Integer modelId, @Param("tid")Integer typeId);

    /***
     * 修改
     * @param saveModel
     */
    void updateModel(SensorModelDO saveModel);

    /**
     * 删除关联
     * @param modelId
     */
    void deleteLink(int modelId);

    /**
     * 删除
     * @param id
     */
    void deleteModel(int id);

    /**
     * 获取模板名称的数量
     * @param name
     * @return
     */
    Integer countModelByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<SensorModelDO> selectAllProductModels();

    /**
     * 根据模型编号进行查询
     * @param modelId
     * @return
     */
    SensorModelDO selectModelById(int modelId);
}
