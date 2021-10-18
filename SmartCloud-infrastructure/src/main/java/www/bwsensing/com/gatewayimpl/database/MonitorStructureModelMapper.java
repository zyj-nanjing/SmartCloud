package www.bwsensing.com.gatewayimpl.database;

import java.util.List;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionModelDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureModelDO;

/**
 * @author macos-zyj
 */
public interface MonitorStructureModelMapper {

    /**
     * 保存模板
     * @param structureModel
     */
    void saveModel(MonitorStructureModelDO structureModel);

    /**
     * 修改模板
     * @param structureModel
     */
    void updateModel(MonitorStructureModelDO structureModel);

    /**
     * 根据结构体编码删除
     * @param modelCode
     */
    void deleteModelByCode(String modelCode);

    /**
     * 获取当前模板的Code
     * @param modelId
     * @return
     */
    String getStructureCodeById(Integer modelId);
    /**
     * 获取结构物模板
     * @param id
     * @return
     */
    MonitorStructureModelDO selectStructureModelById(Integer id);

    /**
     * 获取结构物模板
     * @param code
     * @return
     */
    MonitorStructureModelDO selectStructureModelByCode(String code);

    /**
     * 查询结构物模板使用次数
     * @param code
     * @return
     */
    Integer countStructureModelUseByCode(String code);
    /**
     * 查询结构物模板
     * @param groupId 小组编号
     * @return
     */
    List<MonitorStructureModelDO> selectStructureModel(Integer groupId);

    /**
     * 查询测点模板
     * @param code
     * @return
     */
    List<MonitorPositionModelDO> selectMonitorPositionModelByCode(String code);
}
