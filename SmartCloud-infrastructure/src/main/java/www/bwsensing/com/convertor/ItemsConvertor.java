package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.MonitorItem;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;

/**
 * 转换器
 * @author macos-zyj
 */
public class ItemsConvertor {
    private static final BeanCopier MONITOR_ITEM_COPIER = BeanCopier.create(MonitorItem.class, MonitorItemsDO.class,false);
    private static final BeanCopier MONITOR_ITEM_DO_COPIER = BeanCopier.create(MonitorItemsDO.class, MonitorItem.class,false);

    public static MonitorItem toDomain(MonitorItemsDO itemDo){
        MonitorItem item = new MonitorItem();
        MONITOR_ITEM_DO_COPIER.copy(itemDo,item,null);
        return item;
    }
}
