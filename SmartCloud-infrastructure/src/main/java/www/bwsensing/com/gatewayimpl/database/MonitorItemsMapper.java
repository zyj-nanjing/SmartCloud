package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;

import java.util.List;

/**
 * 监测项
 * @author macos-zyj
 */
public interface MonitorItemsMapper {

    /**
     * 保存
     * @param monitorItems
     */
    void save(MonitorItemsDO monitorItems);

    /**
     * 修改
     * @param monitorItems
     */
    void update(MonitorItemsDO monitorItems);

    /**
     * 删除（修改状态）
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据类型编号删除
     * @param id
     */
    void deleteTypeLinkById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<MonitorItemsDO> selectItems();
    /**
     * 获取同一类型的所有item
     * @param typeId
     * @return
     */
    List<MonitorItemsDO> selectItemsByTypeId(Integer typeId);

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    MonitorItemsDO selectItemById(Integer id);

    /**
     * 获取传感器型号下的的所有item
     * @param modelId
     * @return
     */
    List<MonitorItemsDO> selectItemsByModelId(Integer modelId);

    /**
     * 根据传感器sn编码获取监测项信息
     * @param sensorSn
     * @return
     */
    List<MonitorItemsDO> selectItemsBySensorSn(String sensorSn);

    /**
     * 根据检测项编码获取检测项
     * @param dataId
     * @return
     */
    MonitorItemsDO selectItemsByDataId(String dataId);
}
