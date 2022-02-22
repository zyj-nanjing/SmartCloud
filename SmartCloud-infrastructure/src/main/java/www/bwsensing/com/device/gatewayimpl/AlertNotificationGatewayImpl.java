package www.bwsensing.com.device.gatewayimpl;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;

import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.device.convertor.AlertRoleConvertor;
import www.bwsensing.com.device.convertor.NotificationConvertor;
import www.bwsensing.com.device.convertor.SensorConvertor;
import www.bwsensing.com.device.convertor.SensorModelConvertor;
import www.bwsensing.com.device.gatewayimpl.database.*;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorModelDO;
import www.bwsensing.com.domain.device.model.alert.AlertNotification;
import www.bwsensing.com.domain.device.gateway.AlertNotificationGateway;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.AlertNotificationPushEvent;
import www.bwsensing.com.system.convertor.OperateGroupConvertor;
import www.bwsensing.com.project.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.system.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.OperateGroupDO;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class AlertNotificationGatewayImpl implements AlertNotificationGateway {
    @Resource
    private SensorModelMapper sensorModelMapper;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private AlertRoleMapper alertRoleMapper;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private AlertNotificationMapper notificationMapper;
    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private OperateGroupMapper operateGroupMapper;
    @Resource
    private DomainEventPublisher domainEventPublisher;

    @Override
    public void receiveNotification(AlertNotification alertNotification) {
        saveNotificationMessage(alertNotification);
        domainEventPublisher.publish(initNotificationPushEvent(alertNotification));
    }

    private void  saveNotificationMessage(AlertNotification alertNotification){
        AlertRoleDO alertRoleDo = alertRoleMapper.getAlertRoleByRoleName(alertNotification.getRoleName());
        if (null == alertRoleDo){
            throw new BizException("CURRENT_ROLE_DELETED","当前规则已被删除无需录入日志");
        }
        alertNotification.initAlertRoleInfo(AlertRoleConvertor.toDomainObject(alertRoleDo));
        SensorDO sensorDo = sensorMapper.selectSensorBySn(alertNotification.getSn());
        alertNotification.initSensorInfo(SensorConvertor.toDomain(sensorDo));
        SensorModelDO sensorModel = sensorModelMapper.selectModelById(alertNotification.getModelId());
        alertNotification.initSensorModel(SensorModelConvertor.toDomainObject(sensorModel));
        OperateGroupDO operateGroup = operateGroupMapper.selectGroupById(alertNotification.getGroupId());
        AlertGroupDO alertGroup = alertGroupMapper.getAlertGroupById(alertNotification.getAlertGroupId());
        alertNotification.setAlertGroupName(alertGroup.getGroupName());
        alertNotification.setPushMethod(alertGroup.getPushType());
        alertNotification.initGroupInfo(OperateGroupConvertor.toDomain(operateGroup));
        if (null != sensorDo.getProjectId()){
            alertNotification.setProjectName(projectMapper.selectMonitorProjectById(sensorDo.getProjectId()).getName());
        }
        notificationMapper.saveNotification(NotificationConvertor.toDataObject(alertNotification));
        log.info("告警消息接收 Alert Time:{} --- 消息内容:{}",alertNotification.getAlertTime(),alertNotification.getSummary());
    }

    private AlertNotificationPushEvent initNotificationPushEvent(AlertNotification alertNotification){
        AlertNotificationPushEvent pushEvent= new AlertNotificationPushEvent();
        pushEvent.setAlertGroupId(alertNotification.getAlertGroupId());
        pushEvent.setSensorName(alertNotification.getSensorName());
        pushEvent.setSensorModel(alertNotification.getSensorModel());
        pushEvent.setAlertTime(alertNotification.getAlertTime());
        pushEvent.setAlertName(alertNotification.getAlertName());
        pushEvent.setCurrentProject(alertNotification.getProjectName());
        pushEvent.setSensorSn(alertNotification.getSn());
        pushEvent.setColor(alertNotification.getColor());
        pushEvent.setAlertGroupName(alertNotification.getAlertGroupName());
        pushEvent.setAlertMessage(alertNotification.getSummary());
        pushEvent.setPushMethod(alertNotification.getPushMethod());
        return pushEvent;
    }
}