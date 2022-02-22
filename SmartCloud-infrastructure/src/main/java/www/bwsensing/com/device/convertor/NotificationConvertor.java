package www.bwsensing.com.device.convertor;


import org.springframework.beans.BeanUtils;
import www.bwsensing.com.domain.device.model.alert.AlertNotification;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertNotificationDO;

/**
 * @author macos-zyj
 */
public class NotificationConvertor {

    public static AlertNotificationDO toDataObject(AlertNotification notification){
        AlertNotificationDO dataObject = new AlertNotificationDO();
        BeanUtils.copyProperties(notification,dataObject);
        return dataObject;
    }
}
