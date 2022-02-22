package www.bwsensing.com.device.command;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.device.dto.command.AlertRoleBindCmd;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.device.model.SensorInfo;
import www.bwsensing.com.domain.device.gateway.SensorGateway;
import www.bwsensing.com.domain.device.gateway.AlertRoleGateway;
import www.bwsensing.com.domain.device.model.alert.AlertTemplate;
import www.bwsensing.com.domain.device.gateway.AlertTemplateGateway;

/**
 * @author macos-zyj
 */
@Component
public class AlertRoleBindCmdExo {
    @Resource
    private SensorGateway sensorGateway;
    @Resource
    private AlertTemplateGateway alertTemplateGateway;
    @Resource
    private AlertRoleGateway alertRoleGateway;

    public Response execute(AlertRoleBindCmd bindCmd){
        SensorInfo sensorInfo = sensorGateway.getSensorInfoById(bindCmd.getSensorId());
        AlertTemplate alertTemplate = alertTemplateGateway.getAlertTemplateById(bindCmd.getTemplateId());
        if (!alertTemplate.getSensorModel().getId().equals(sensorInfo.getModelId())){
            throw new BizException("SENSOR_MODEL_NOT_EQUAL","传感器型号与预警模板对应型号不一样!");
        }
        alertRoleGateway.saveAlertRoles(alertTemplate.initializeRole(sensorInfo));
        return Response.buildSuccess();
    }

}
