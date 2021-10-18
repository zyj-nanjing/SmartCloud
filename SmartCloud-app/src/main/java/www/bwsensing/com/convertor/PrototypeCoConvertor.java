package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.monitor.MonitorPrototype;
import www.bwsensing.com.dto.clientobject.PrototypeCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorPrototypeDO;

import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class PrototypeCoConvertor {
    private static final BeanCopier MONITOR_TYPE_COPIER = BeanCopier.create(MonitorPrototypeDO.class, PrototypeCO.class,false);

    private static final BeanCopier MONITOR_TYPE_DOMAIN_COPIER = BeanCopier.create(PrototypeCO.class, MonitorPrototype.class,false);

    public static List<PrototypeCO> toCoListByDo(List<MonitorPrototypeDO> typeList){
        if (null != typeList){
            return typeList.stream().map(PrototypeCoConvertor::toCo).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static PrototypeCO toCo(MonitorPrototypeDO prototypeDO){
        PrototypeCO prototypeCo = new PrototypeCO();
        MONITOR_TYPE_COPIER.copy(prototypeDO,prototypeCo,null);
        return prototypeCo;
    }

    public static MonitorPrototype toDomain(PrototypeCO prototypeCo){
        MonitorPrototype prototype = new MonitorPrototype();
        MONITOR_TYPE_DOMAIN_COPIER.copy(prototypeCo,prototype,null);
        return prototype;
    }
}
