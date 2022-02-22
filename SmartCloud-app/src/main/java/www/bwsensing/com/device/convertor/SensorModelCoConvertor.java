package www.bwsensing.com.device.convertor;

import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.SensorModelCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorModelDO;
import java.util.ArrayList;
import java.util.List;

/**
 * 传感器模板转换
 * @author macos-zyj
 */
public class SensorModelCoConvertor {
    private static final BeanCopier SENSOR_MODEL_DATA__COPIER = BeanCopier.create(SensorModelDO.class, SensorModelCO.class,false);

    public static List<SensorModelCO> toClientObjectArray(List<SensorModelDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SensorModelCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static SensorModelCO toClientObject(SensorModelDO sensorModel){
        SensorModelCO sensorModelCo = new SensorModelCO();
        SENSOR_MODEL_DATA__COPIER.copy(sensorModel,sensorModelCo,null);
        return sensorModelCo;
    }
}