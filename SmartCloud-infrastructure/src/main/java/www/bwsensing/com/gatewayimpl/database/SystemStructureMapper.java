package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.SystemStructureDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemStructureMapper {
    /**
     * 查询系统组织
     *
     * @param id 系统组织主键
     * @return 系统组织
     */
    SystemStructureDO getStructureById(Integer id);

    /**
     * 查询系统组织列表
     *
     * @param queryData 系统组织
     * @return 系统组织集合
     */
    List<SystemStructureDO> selectStructureByQuery(SystemStructureDO queryData);

    /**
     * 新增系统组织
     *
     * @param systemStructureDO 系统组织
     * @return 结果
     */
    int addStructure(SystemStructureDO systemStructureDO);

    /**
     * 修改系统组织
     *
     * @param systemStructureDO 系统组织
     * @return 结果
     */
    int updateStructure(SystemStructureDO systemStructureDO);

    /**
     * 删除系统组织
     *
     * @param id 系统组织主键
     * @return 结果
     */
    int deleteById(Integer id);

    /**
     * 批量删除系统组织
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteByIds(String[] ids);
}
