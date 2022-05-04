package www.bwsensing.com.device.command;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.convertor.ProductDataItemConvertor;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.alert.AlertParam;
import www.bwsensing.com.domain.device.model.alert.AlertRole;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.device.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;

/**
 * @author macos-zyj
 */
@Component
public class AlertRoleAddCmdExo {
    @Resource
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private AlertRoleGateway alertRoleGateway;
    @Resource
    private ProductDataItemMapper itemMapper;
    @Resource
    private AlertGroupMapper alertGroupMapper;

    public Response execute(AlertRoleAddCmd saveCmd){
        AlertGroupDO alertGroup = alertGroupMapper.getAlertGroupById(saveCmd.getAlertGroupId());
        ProductDevice sensorInfo = productDeviceGateway.getDeviceDetailById(alertGroup.getCurrentSensorId());
        AlertParam alertParam = new AlertParam();
        BeanUtils.copyProperties(saveCmd,alertParam);
        ProductDataItemDO itemsDo = itemMapper.getProductDataItemById(alertParam.getParamNo());
        alertParam.setMonitorItem(ProductDataItemConvertor.toDomain(itemsDo));
        AlertRole saveRole = new AlertRole(sensorInfo,alertParam);
        saveRole.setAlertGroupId(saveCmd.getAlertGroupId());
        alertRoleGateway.saveAlertRole(saveRole);
        return Response.buildSuccess();
    }
}
