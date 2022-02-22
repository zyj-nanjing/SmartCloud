package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.role.Permission;
import www.bwsensing.com.domain.system.model.role.UserRole;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface PermissionGateway {
    /**
     * 同步权限表与菜单表
     */
    void synchronizationPermission();

    /**
     * 根据用户编号获取所有角色
     * @param userId
     * @return
     */
    List<UserRole> listRolesByUserId(Integer userId);

    /**
     * 根据RoleID获取权限路由
     * @param roleId
     * @return
     */
    List<Permission> getPermissionsByRoleId(Integer roleId);
}
