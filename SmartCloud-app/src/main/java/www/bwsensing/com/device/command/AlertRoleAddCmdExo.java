package www.bwsensing.com.device.command;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.monitor.convertor.ItemsConvertor;
import www.bwsensing.com.domain.device.model.SensorInfo;
import www.bwsensing.com.domain.device.model.alert.AlertParam;
import www.bwsensing.com.domain.device.model.alert.AlertRole;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.device.gateway.SensorGateway;
import www.bwsensing.com.device.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.device.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorItemsDO;

/**
 * @author macos-zyj
 */
@Component
public class AlertRoleAddCmdExo {
    @Resource
    private SensorGateway sensorGateway;
    @Resource
    private AlertRoleGateway alertRoleGateway;
    @Resource
    private MonitorItemsMapper itemMapper;
    @Resource
    private AlertGroupMapper alertGroupMapper;

    public Response execute(AlertRoleAddCmd saveCmd){
        AlertGroupDO alertGroup = alertGroupMapper.getAlertGroupById(saveCmd.getAlertGroupId());
        SensorInfo sensorInfo = sensorGateway.getSensorInfoById(alertGroup.getCurrentSensorId());
        AlertParam alertParam = new AlertParam();
        BeanUtils.copyProperties(saveCmd,alertParam);
        MonitorItemsDO itemsDo = itemMapper.selectItemById(alertParam.getParamNo());
        alertParam.setMonitorItem(ItemsConvertor.toDomain(itemsDo));
        AlertRole saveRole = new AlertRole(sensorInfo,alertParam);
        saveRole.setAlertGroupId(saveCmd.getAlertGroupId());
        alertRoleGateway.saveAlertRole(saveRole);
        return Response.buildSuccess();
    }
}
