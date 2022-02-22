package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.constant.RoleConstant;
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
        if(null == alertGroupMapper.getAlertGroupById(id)){
            throw new BizException("NO_GROUP_FOUND","该组织不存在!");
        }
        if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            throw new BizException("NO_PERMISSION_DELETE","无权进行删除!");
        }
        alertGroupMapper.deleteAlertGroupById(id);
        memberMapper.deleteNotificationMemberByGroupId(id);
        List<AlertRoleDO> roleCollection = alertRoleMapper.selectAlertRoleByGroupId(id);
        roleCollection.forEach(role->alertRoleGateway.deleteAlertRole(role.getId()));
    }
}
