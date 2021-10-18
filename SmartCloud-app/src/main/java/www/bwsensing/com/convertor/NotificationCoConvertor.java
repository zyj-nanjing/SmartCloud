package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertNotificationDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * 通知消息转换器
 * @author macos-zyj
 */
public class NotificationCoConvertor {
    private static final BeanCopier CLIENT_COPIER = BeanCopier.create(AlertNotificationDO.class, NotificationCO.class,false);

    public static NotificationCO toClientObject(AlertNotificationDO dataObject){
        NotificationCO clientObject = new NotificationCO();
        CLIENT_COPIER.copy(dataObject,clientObject,null);
        return  clientObject;
    }

    public static List<NotificationCO> toClientCollection(List<AlertNotificationDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(NotificationCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
