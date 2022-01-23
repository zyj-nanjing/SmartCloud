package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.PermissionDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface PermissionMapper {
    /**
     * 保存
     * @param permission
     */
    void save(PermissionDO permission);

    /**
     * 修改
     * @param permission
     */
    void update(PermissionDO  permission);

    /**
     * 清理所有
     */
    void clearAll();

    /**
     * 角色权限关联
     * @param roleId
     * @param permissionId
     */
    void insertRolePermission(Integer roleId,Integer permissionId);

    /**
     * 查询所有权限
     * @return
     */
    List<PermissionDO> selectPermission();

    /**
     * 根据对应的角色编号获取路由列表
     * @param roleId
     * @return
     */
    List<PermissionDO> selectPermissionsByRoleId(Integer roleId);

    /**
     * 根据角色编码查询权限
     * @param roleCode
     * @return
     */
    List<PermissionDO> selectPermissionByRoleCode(String roleCode);
}
