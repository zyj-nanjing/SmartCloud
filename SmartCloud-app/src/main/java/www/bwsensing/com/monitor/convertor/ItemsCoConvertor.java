package www.bwsensing.com.monitor.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.monitor.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class ItemsCoConvertor {
    private static final BeanCopier MONITOR_ITEM_COPIER = BeanCopier.create(ProductDataItemDO.class, MonitorItemsCO.class,false);

    public  static MonitorItemsCO toClientObject(ProductDataItemDO dataObject){
        MonitorItemsCO clientObject = new MonitorItemsCO();
        MONITOR_ITEM_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<MonitorItemsCO> toClientObjectList(List<ProductDataItemDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(ItemsCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
