package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.domain.project.model.ProjectRoleEnum;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.convertor.AlertGroupConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertGroup;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.device.gateway.AlertGroupGateway;
import www.bwsensing.com.device.gatewayimpl.database.AlertRoleMapper;
import www.bwsensing.com.device.gatewayimpl.database.NotificationMemberMapper;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;
import www.bwsensing.com.project.gatewayimpl.database.MonitorProjectMapper;

/**
 * @author macos-zyj
 */
@Component
public class AlertGroupGatewayImpl implements AlertGroupGateway {
    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private AlertRoleMapper alertRoleMapper;
    @Resource
    private AlertRoleGateway alertRoleGateway;
    @Resource
    private NotificationMemberMapper memberMapper;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveGroup(AlertGroup saveGroup) {
        AlertGroupDO saveData = AlertGroupConvertor.toDataObject(saveGroup);
        alertGroupMapper.saveAlertGroup(saveData);
        saveGroup.getRoleCollection().forEach(role ->{
           role.setAlertGroupId(saveData.getId());
           role.insertGroupInfo();
           alertRoleGateway.saveAlertRole(role);
        });
    }

    @Override
    public void updateGroup(AlertGroup updateGroup) {
        AlertGroupDO updateData = AlertGroupConvertor.toDataObject(updateGroup);
        alertGroupMapper.updateAlertGroup(updateData);
    }

    @Transactional(rollbackFor=RuntimeException.class)
    @Override
    public void deleteGroup(Integer id) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        AlertGroupDO alertGroup = alertGroupMapper.getAlertGroupById(id);
        if(null == alertGroup){
            throw new BizException("NO_GROUP_FOUND","该告警分组不存在!");
        }
        ProductDeviceDO device = productDeviceMapper.getProductDetailById(alertGroup.getCurrentSensorId());
        String projectCode = projectMapper.getProjectRoleByUserId(device.getProjectId(), tokenData.getUserId());
        Assert.notNull(projectCode,"无权进行删除");
        ProjectRoleEnum projectRole = ProjectRoleEnum.getProjectRoleByCode(projectCode);
        if (ProjectRoleEnum.PROJECT_OWNER.equals(projectRole)||ProjectRoleEnum.PROJECT_MANAGER.equals(projectRole)){
            alertGroupMapper.deleteAlertGroupById(id);
            memberMapper.deleteNotificationMemberByGroupId(id);
            List<AlertRoleDO> roleCollection = alertRoleMapper.selectAlertRoleByGroupId(id);
            roleCollection.forEach(role->alertRoleGateway.deleteAlertRole(role.getId()));
        } else {
            throw new BizException("NO_PERMISSION_DELETE","无权进行删除!");
        }
    }
}
