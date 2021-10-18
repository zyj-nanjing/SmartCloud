package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPositionModelDO;

/**
 * @author macos-zyj
 */
public interface MonitorPositionModelMapper {
    /**
     * 保存
     * @param positionModelDO
     */
    void  save(MonitorPositionModelDO positionModelDO);

    /**
     * 修改
     * @param positionModelDO
     */
    void update(MonitorPositionModelDO positionModelDO);

    /**
     * 根据编号获取测点模板
     * @param id
     * @return
     */
    MonitorPositionModelDO selectPositionModelById(Integer id);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 根据结构物Code删除
     * @param code
     */
    void deleteByStructureCode(String code);
}
