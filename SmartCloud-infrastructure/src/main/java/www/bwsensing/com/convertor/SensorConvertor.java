package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * 设备转换器
 * @author macos-zyj
 */
public class SensorConvertor {
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(SensorDO.class, SensorInfo.class,false);
    private static final BeanCopier  DATA_OBJECT_COPIER= BeanCopier.create(SensorInfo.class, SensorDO.class,false);

    public static SensorInfo toDomain(SensorDO sensorDO){
        SensorInfo sensorInfo = new SensorInfo();
        DOMAIN_OBJECT_COPIER.copy(sensorDO,sensorInfo,null);
        return sensorInfo;
    }

    public static List<SensorInfo> toDomainCollection(List<SensorDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(SensorConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static SensorDO toDataObject(SensorInfo sensorInfo){
        SensorDO sensorDO = new SensorDO();
        DATA_OBJECT_COPIER.copy(sensorInfo,sensorDO,null);
        return sensorDO;
    }
}
