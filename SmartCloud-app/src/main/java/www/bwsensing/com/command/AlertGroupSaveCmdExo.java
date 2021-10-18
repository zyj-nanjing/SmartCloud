package www.bwsensing.com.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import static java.util.stream.Collectors.toList;

import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.ItemsConvertor;
import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.domain.device.alert.AlertGroup;
import www.bwsensing.com.domain.device.alert.AlertParam;
import www.bwsensing.com.domain.device.alert.AlertTemplate;
import www.bwsensing.com.domain.device.alert.NotificationMethod;
import www.bwsensing.com.domain.gateway.*;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;

import javax.annotation.Resource;
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
    private SensorGateway sensorGateway;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemUserGateway userGateway;
    @Resource
    private MonitorItemsMapper itemMapper;

    public Response execute(AlertGroupSaveCmd saveCmd){
        SensorInfo selectedSensor = sensorGateway.getSensorInfoById(saveCmd.getSensorId());
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
        saveGroup.setNotificationMethod(NotificationMethod.getNotificationMethod(saveCmd.getPushMethod()));
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
        MonitorItemsDO itemsDo = itemMapper.selectItemById(alertParam.getParamNo());
        Assert.notNull(itemsDo,"预警项不存在!");
        alertParam.setMonitorItem(ItemsConvertor.toDomain(itemsDo));
        alertParam.create();
        return  alertParam;
    }
}
