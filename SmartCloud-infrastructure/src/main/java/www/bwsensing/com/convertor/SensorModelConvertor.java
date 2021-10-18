package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.SensorModel;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorModelDO;

/**
 * @author macos-zyj
 */
public class SensorModelConvertor {
    private static final BeanCopier MODEL_DATA_COPY = BeanCopier.create(SensorModel.class,
            SensorModelDO.class,false);
    private static final BeanCopier MODEL_DOMAIN_COPY = BeanCopier.create(SensorModelDO.class,
            SensorModel.class,false);

    public static SensorModel toDomainObject(SensorModelDO sensorModelDo){
        SensorModel sensorModel = new SensorModel();
        MODEL_DOMAIN_COPY.copy(sensorModelDo,sensorModel,null);
        return sensorModel;
    }


    public static SensorModelDO toDataObject(SensorModel sensorModel){
        SensorModelDO sensorModelDo = new SensorModelDO();
        MODEL_DATA_COPY.copy(sensorModel,sensorModelDo,null);
        return sensorModelDo;
    }
}
