package www.bwsensing.com.system.gatewayimpl.database;

import www.bwsensing.com.system.gatewayimpl.database.dataobject.OperateGroupDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface OperateGroupMapper {

    /**
     * 保存
     * @param groupDo
     */
    void save(OperateGroupDO groupDo);

    /**
     * 修改
     * @param groupDo
     */
    void update(OperateGroupDO groupDo);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 查询
     * @return
     */
    List<OperateGroupDO>  selectOperateGroups();

    /**
     * Id查询
     * @param id
     * @return
     */
    OperateGroupDO selectGroupById(int id);

    /**
     * 组编号
     * @param groupCode
     * @return
     */
    Integer selectGroupCode(String groupCode);
}
