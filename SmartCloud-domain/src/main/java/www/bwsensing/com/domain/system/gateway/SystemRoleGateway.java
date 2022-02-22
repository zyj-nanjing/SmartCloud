package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.role.UserRole;

/**
 * 系统角色领域网关
 * @author macos-zyj
 */
public interface SystemRoleGateway {
    /**
     * 保存系统角色
     * @param userRole
     */
    void saveSystemRole(UserRole userRole);

    /**
     * 修改系统角色
     * @param userRole
     */
    void updateSystemRole(UserRole userRole);
}
