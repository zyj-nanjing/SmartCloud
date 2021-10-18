package www.bwsensing.com.command;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.ItemsConvertor;
import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.domain.device.alert.AlertParam;
import www.bwsensing.com.domain.device.alert.AlertRole;
import www.bwsensing.com.domain.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.gateway.SensorGateway;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;

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
        alertRoleGateway.saveAlertRole(saveRole);
        return Response.buildSuccess();
    }
}
