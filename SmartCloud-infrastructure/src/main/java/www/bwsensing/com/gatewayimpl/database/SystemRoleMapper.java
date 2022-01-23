package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemRoleMapper {
    /**
     * 获取所有角色
     * @return
     */
    List<SystemRoleDO> selectAllRole();

    /**
     * 组合查询
     * @param role
     * @return
     */
    List<SystemRoleDO> selectRoleBySort(SystemRoleDO role);

    /**
     * 查询用户与角色的关联
     * @param userId
     * @return
     */
    List<SystemRoleDO> selectRoleByUserId(Integer userId);

    /***
     * 根据角色Code 获取
     * @param roleCode
     * @return
     */
    SystemRoleDO getUserRoleByCode(String roleCode);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    SystemRoleDO getUserRoleById(Integer id);

    /***
     * 根据权限编码查询角色数目
     * @param roleCode
     * @return
     */
    Integer countUserRoleSizeByCode(String roleCode);

    /**
     * 根据权限名称查询角色数目
     * @param roleName
     * @return
     */
    Integer countUserRoleByName(String roleName);

    /**
     * 保存
     * @param role
     */
    void saveUserRole(SystemRoleDO role);

    /**
     * 修改
     * @param role
     */
    void updateUserRole(SystemRoleDO role);

    /**
     * 清除当前角色对应的所有菜单
     * @param roleId
     */
    void deleteMenuRoleByRoleId(Integer roleId);

    /**
     * 保存菜单和角色的关联
     * @param roleId
     * @param menuId
     */
    void insertMenuRole(@Param("roleId")Integer roleId, @Param("menuId")Integer menuId);
}
