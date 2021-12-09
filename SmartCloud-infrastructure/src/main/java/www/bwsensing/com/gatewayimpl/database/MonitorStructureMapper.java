package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorStructureDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface MonitorStructureMapper {
    /**
     * 保存
     * @param monitorStructure
     */
    void  save(MonitorStructureDO monitorStructure);

    /**
     * 修改
     * @param monitorStructure
     */
    void update(MonitorStructureDO monitorStructure);

    /**
     * 根据项目编号进行删除!
     * @param projectId
     */
    void deleteByProjectId(Integer projectId);
    /**
     * 查询
     * @return
     */
    List<MonitorStructureDO> selectMonitorStructure();

    /**
     * 根据编号查询测点
     * @param sid
     * @return
     */
    List<MonitorPositionDO> selectMonitorPositionBySid(Integer sid);

    /**
     * ID查询
     * @param id
     * @return
     */
    MonitorStructureDO selectStructureById(Integer id);

    /**
     * 根据项目编号进行删除
     * @param projectId
     * @return
     */
    List<MonitorStructureDO> selectStructureByProjectId(Integer projectId);
}
