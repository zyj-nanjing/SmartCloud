package www.bwsensing.com.device.tcp;

import www.bwsensing.com.domain.device.model.alert.AlertRole;

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
