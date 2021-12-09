package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.NotificationMsgCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.NotificationTag;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
public class NotificationMsgCoConvertor {
    private static final BeanCopier MSG_COPIER = BeanCopier.create(NotificationTag.class, NotificationMsgCO.class,false);

    public static NotificationMsgCO toClientObject(NotificationTag dataObject){
        NotificationMsgCO clientObject = new NotificationMsgCO();
        MSG_COPIER.copy(dataObject,clientObject,null);
        return  clientObject;
    }

    public static List<NotificationMsgCO> toClientCollection(List<NotificationTag> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(NotificationMsgCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
