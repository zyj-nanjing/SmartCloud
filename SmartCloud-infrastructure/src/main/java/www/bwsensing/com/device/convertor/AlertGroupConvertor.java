package www.bwsensing.com.device.convertor;

import www.bwsensing.com.domain.device.model.alert.AlertGroup;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import java.util.ArrayList;
import java.util.List;

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
        if (null != domainObject.getNotificationMethods()){
            List<String> typedValues = new ArrayList<>();
            domainObject.getNotificationMethods().forEach(method -> typedValues.add(method.getTypeId()+""));
            alertGroup.setPushTypes(String.join("#", typedValues));
        }
        alertGroup.setGroupName(domainObject.getGroupName());
        alertGroup.setOperateGroupId(domainObject.getOperateGroupId());
        alertGroup.setId(domainObject.getId());
        if (null != domainObject.getCurrentUser()){
            alertGroup.setCreator(domainObject.getCurrentUser().getAccountName());
        }
        return alertGroup;
    }
}
