package www.bwsensing.com.monitor.gatewayimpl.database;

import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorPositionDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface MonitorPositionMapper {
    /**
     * 保存
     * @param positionDo
     */
    void save(MonitorPositionDO positionDo);


    /**
     * 修改
     * @param positionDo
     */
    void update(MonitorPositionDO positionDo);

    /**
     * 根据编号获取测点
     * @param id
     * @return
     */
    MonitorPositionDO selectPositionById(Integer id);

    /**
     * 根据结构物编号获取测点
     * @param structureId
     * @return
     */
    List<MonitorPositionDO> getPositionsByStructureId(Integer structureId);
    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 结构物编号
     * @param structureId
     */
    void deleteByStructureId(Integer structureId);
}
