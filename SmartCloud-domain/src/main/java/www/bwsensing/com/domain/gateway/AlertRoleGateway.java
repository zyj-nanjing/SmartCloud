package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.device.alert.AlertRole;
import java.util.List;

/**
 * 监测原则 领域网关
 * @author macos-zyj
 */
public interface AlertRoleGateway {
    /**
     * 保存监测原则集合并推送
     * @param alertRoles
     */
    void saveAlertRoles(List<AlertRole> alertRoles);

    /**
     * 保存监测原则并推送
     * @param alertRole
     */
    void saveAlertRole(AlertRole alertRole);

    /**
     * 修改并推送监测原则
     * @param alertRole
     */
    void updateAlertRole(AlertRole alertRole);

    /**
     * 挂起预警规则
     * @param roleId
     */
    void suspendAlertRole(Integer roleId);

    /**
     * 恢复预警规则
     * @param roleId
     */
    void resumeAlertRole(Integer roleId);
    /**
     * 删除监测规则
     * @param roleId
     */
    void deleteAlertRole(Integer roleId);


    /**
     * 根据规则编号获取监测规则
     * @param roleId
     * @return
     */
    AlertRole selectAlertRoleById(Integer roleId);
}
