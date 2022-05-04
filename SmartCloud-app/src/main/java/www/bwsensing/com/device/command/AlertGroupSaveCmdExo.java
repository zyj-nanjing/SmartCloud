package www.bwsensing.com.device.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import static java.util.stream.Collectors.toList;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.device.convertor.ProductDataItemConvertor;
import www.bwsensing.com.domain.device.gateway.AlertGroupGateway;
import www.bwsensing.com.domain.device.gateway.AlertTemplateGateway;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.alert.AlertGroup;
import www.bwsensing.com.domain.device.model.alert.AlertParam;
import www.bwsensing.com.domain.device.model.alert.AlertTemplate;
import www.bwsensing.com.domain.device.model.alert.NotificationMethod;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.device.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.device.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
public class AlertGroupSaveCmdExo {
    @Resource
    private AlertTemplateGateway templateGateway;
    @Resource
    private AlertGroupGateway alertGroupGateway;
    @Resource
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemUserGateway userGateway;
    @Resource
    private ProductDataItemMapper itemMapper;

    public Response execute(AlertGroupSaveCmd saveCmd){
        ProductDevice selectedSensor = productDeviceGateway.getDeviceDetailById(saveCmd.getSensorId());
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser currentUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        AlertGroup saveGroup;
        if (saveCmd.getIsTemplate()){
            Assert.notNull(saveCmd.getTemplateId(),"模板编号不能为空!");
            AlertTemplate alertTemplate = templateGateway.getAlertTemplateById(saveCmd.getTemplateId());
            saveGroup = new AlertGroup(saveCmd.getGroupName(),selectedSensor,currentUser,alertTemplate);
        } else {
            if (saveCmd.getRoleCollection().size()<=0){
                throw new BizException("ALERT_ROLES_NOT_NULL","预警参数不能为空");
            }
            List<AlertParam> paramList = saveCmd.getRoleCollection().stream().map(this::validAlertRole).collect(toList());
            saveGroup = new AlertGroup(saveCmd.getGroupName(),selectedSensor,currentUser,paramList);
        }
        saveGroup.setOperateGroupId(tokenData.getGroupId());
        saveGroup.setNotificationMethods(new ArrayList<>());
        for (Integer pushId:saveCmd.getPushMethods()){
            saveGroup.getNotificationMethods().add(NotificationMethod.getNotificationMethod(pushId));
        }
        alertGroupGateway.saveGroup(saveGroup);
        return Response.buildSuccess();
    }

    private AlertParam validAlertRole(AlertRoleAddCmd roleAddCmd){
        AlertParam alertParam = new AlertParam();
        BeanUtils.copyProperties(roleAddCmd,alertParam);
        Assert.isTrue(StringUtils.isNotEmpty(alertParam.getAlertName()),"预警名称不能为空!");
        Assert.notNull(alertParam.getParamNo(),"预警检测项不能为空!");
        Assert.isTrue(StringUtils.isNotEmpty(alertParam.getSummary()),"告警消息不能为空!");
        Assert.isTrue(StringUtils.isNotEmpty(alertParam.getColor()),"颜色不能为空!");
        Assert.notNull(alertParam.getFormulas(),"预警公式不能为空!");
        Assert.isTrue(alertParam.getFormulas().size()>0,"预警公式不能为空!");
        ProductDataItemDO itemsDo = itemMapper.getProductDataItemById(alertParam.getParamNo());
        Assert.notNull(itemsDo,"预警项不存在!");
        alertParam.setMonitorItem(ProductDataItemConvertor.toDomain(itemsDo));
        alertParam.create();
        return  alertParam;
    }
}
