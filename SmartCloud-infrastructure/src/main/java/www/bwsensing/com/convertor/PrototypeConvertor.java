package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.MonitorPrototype;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPrototypeDO;

/**
 * 转换器
 * @author macos-zyj
 */
public class PrototypeConvertor {

    private static final BeanCopier MONITOR_TYPE_COPIER = BeanCopier.create(MonitorPrototype.class, MonitorPrototypeDO.class,false);
    private static final BeanCopier MONITOR_TYPE_DO_COPIER = BeanCopier.create(MonitorPrototypeDO.class, MonitorPrototype.class,false);

    public static MonitorPrototype toDomain(MonitorPrototypeDO prototypeDo){
        MonitorPrototype monitorPrototype = new MonitorPrototype();
        MONITOR_TYPE_DO_COPIER.copy(prototypeDo,monitorPrototype,null);
        return monitorPrototype;
    }

    public static MonitorPrototypeDO toDataObject(MonitorPrototype prototype){
        MonitorPrototypeDO prototypeDo = new MonitorPrototypeDO();
        MONITOR_TYPE_COPIER.copy(prototype,prototypeDo,null);
        return prototypeDo;
    }
}
