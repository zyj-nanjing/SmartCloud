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
     * 查询所有权限
     * @return
     */
    List<PermissionDO> selectPermission();

    /**
     * 根据角色编码查询权限
     * @param roleCode
     * @return
     */
    List<PermissionDO> selectPermissionByRoleCode(String roleCode);
}
