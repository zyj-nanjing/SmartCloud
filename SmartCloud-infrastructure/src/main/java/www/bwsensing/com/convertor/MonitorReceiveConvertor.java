package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorReceiveDO;

/**
 * 监测日志转换器
 * @author macos-zyj
 */
public class MonitorReceiveConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER =
            BeanCopier.create(MonitorReceive.class, MonitorReceiveDO.class,false);

    public static MonitorReceiveDO toDataObject(MonitorReceive domainObject){
        MonitorReceiveDO dataObject = new MonitorReceiveDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }
}
