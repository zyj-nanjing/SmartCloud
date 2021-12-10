package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.AlertRoleDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface AlertRoleMapper {
    /**
     * 条件组合查询
     * @param querySort
     * @return
     */
    List<AlertRoleDO> selectAlertRoleBySort(AlertRoleDO querySort);

    /**
     * 根据预警分组编号进行删除
     * @param groupId
     * @return
     */
    List<AlertRoleDO> selectAlertRoleByGroupId(Integer groupId);

    /**
     * 按照ID获取预警规则
     * @param id
     * @return
     */
    AlertRoleDO getAlertRoleById(Integer id);
    /**
     * 根据规则名称获取
     * @param roleName
     * @return
     */
    AlertRoleDO getAlertRoleByRoleName(String roleName);
    /**
     * 保存预警规则
     * @param saveRole
     */
    void saveAlertRole(AlertRoleDO saveRole);

    /**
     * 修改预警规则
     * @param updateRole
     */
    void updateAlertRole(AlertRoleDO updateRole);

    /**
     * 删除预警规则
     * @param id
     */
    void deleteAlertRoleById(Integer id);
}
