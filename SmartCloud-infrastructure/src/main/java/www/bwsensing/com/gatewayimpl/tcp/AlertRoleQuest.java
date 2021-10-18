package www.bwsensing.com.gatewayimpl.tcp;

import www.bwsensing.com.domain.device.alert.AlertRole;

/**
 * @author macos-zyj
 */
public interface AlertRoleQuest {
    /**
     * 提交预警规则
     * @param alertRole
     */
    void submitAlertRole(AlertRole alertRole);

    /**
     * 删除规则
     * @param roleName
     */
    void deleteAlertRole(String roleName);
}
