package www.bwsensing.com.device.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.device.convertor.AlertRoleConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertRole;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.device.gatewayimpl.database.AlertRoleMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;
import www.bwsensing.com.device.tcp.AlertRoleQuest;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class AlertRoleGatewayImpl implements AlertRoleGateway {

    @Value("${system.alert.forward}")
    private String alertForward;
    @Resource
    private AlertRoleQuest alertRoleQuest;

    @Resource
    private AlertRoleMapper alertRoleMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveAlertRoles(List<AlertRole> alertRoles) {
        alertRoles.forEach(this::saveAlertRole);
    }

    @Override
    public void saveAlertRole(AlertRole alertRole) {
        String alertSql = alertRole.getTemplateSql().replace("[forward]",alertForward);
        alertRole.setForward(alertForward);
        alertRole.setStateSql(alertSql);
        alertRoleMapper.saveAlertRole(AlertRoleConvertor.toDataObject(alertRole));
        alertRoleQuest.submitAlertRole(alertRole);
    }

    @Override
    public void updateAlertRole(AlertRole alertRole) {
        alertRole.updateAlertRole();
        alertRoleMapper.updateAlertRole(AlertRoleConvertor.toDataObject(alertRole));
        alertRoleQuest.submitAlertRole(alertRole);
    }

    @Override
    public void suspendAlertRole(Integer roleId) {

    }

    @Override
    public void resumeAlertRole(Integer roleId) {

    }

    @Override
    public void deleteAlertRole(Integer roleId) {
        AlertRole alertRole = selectAlertRoleById(roleId);
        alertRoleMapper.deleteAlertRoleById(roleId);
        alertRoleQuest.deleteAlertRole(alertRole.getName());
    }

    @Override
    public AlertRole selectAlertRoleById(Integer roleId) {
        AlertRoleDO dataObject = alertRoleMapper.getAlertRoleById(roleId);
        if(null != dataObject){
            return AlertRoleConvertor.toDomainObject(dataObject);
        }
        throw new BizException("ALERT_ROLE_NOT_FOUND","告警规则不存在");
    }
}
