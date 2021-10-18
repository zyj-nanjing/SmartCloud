package www.bwsensing.com.convertor;

import www.bwsensing.com.domain.device.alert.AlertGroup;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertGroupDO;

/**
 * @author macos-zyj
 */
public class AlertGroupConvertor {

    /**
     * 实体数据转换
     * @param domainObject
     * @return
     */
    public static AlertGroupDO toDataObject(AlertGroup domainObject){
        AlertGroupDO alertGroup = new AlertGroupDO();
        if (null != domainObject.getCurrentSensor()){
            alertGroup.setCurrentSensorId(domainObject.getCurrentSensor().getId());
        }
        if (null != domainObject.getCurrentTemplate()){
            alertGroup.setTemplateId(domainObject.getCurrentTemplate().getId());
        }
        alertGroup.setPushType(domainObject.getNotificationMethod().getTypeId());
        alertGroup.setGroupName(domainObject.getGroupName());
        alertGroup.setOperateGroupId(domainObject.getOperateGroupId());
        alertGroup.setId(domainObject.getId());
        if (null != domainObject.getCurrentUser()){
            alertGroup.setCreator(domainObject.getCurrentUser().getAccountName());
        }
        return alertGroup;
    }
}
