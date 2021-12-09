package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.RoleDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface RoleMapper {
    /**
     * 获取所有角色
     * @return
     */
    List<RoleDO> selectAllRole();

    /**
     * 组合查询
     * @param role
     * @return
     */
    List<RoleDO> selectRoleBySort(RoleDO role);

    /***
     * 根据角色Code 获取
     * @param roleCode
     * @return
     */
    RoleDO getUserRoleByCode(String roleCode);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    RoleDO getUserRoleById(Integer id);
    /**
     * 保存
     * @param role
     */
    void saveUserRole(RoleDO role);

    /**
     * 修改
     * @param role
     */
    void updateUserRole(RoleDO role);

}
