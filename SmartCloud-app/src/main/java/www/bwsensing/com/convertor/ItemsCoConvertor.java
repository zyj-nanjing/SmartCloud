package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class ItemsCoConvertor {
    private static final BeanCopier MONITOR_ITEM_COPIER = BeanCopier.create(MonitorItemsDO.class, MonitorItemsCO.class,false);

    public  static MonitorItemsCO toClientObject(MonitorItemsDO dataObject){
        MonitorItemsCO clientObject = new MonitorItemsCO();
        MONITOR_ITEM_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<MonitorItemsCO> toClientObjectList(List<MonitorItemsDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(ItemsCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
