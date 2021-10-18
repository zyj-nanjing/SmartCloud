package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPrototypeDO;
import java.util.List;

/**
 * 监测类型
 * @author macos-zyj
 */
public interface MonitorPrototypeMapper {
    /**
     * 保存
     * @param  prototype
     */
    void save(MonitorPrototypeDO prototype);

    /**
     * 修改
     * @param prototype
     */
    void update(MonitorPrototypeDO prototype);


    /**
     * 添加监测原型与检测项关联
     * @param typeId 监测原型
     * @param itemId 监测项编号
     */
    void addPrototypeLink(@Param("typeId")Integer typeId,@Param("itemId")Integer itemId);


    /**
     * 删除（修改状态）
     * @param id
     */
    void delete(Integer id);

    /**
     * 组合查询
     * @param querySort
     * @return
     */
    List<MonitorPrototypeDO> selectPrototypeBySort(MonitorPrototypeDO  querySort);

    /**
     * 按照ID 查询
     * @param id
     * @return
     */
    MonitorPrototypeDO selectPrototypeById(int id);
}
