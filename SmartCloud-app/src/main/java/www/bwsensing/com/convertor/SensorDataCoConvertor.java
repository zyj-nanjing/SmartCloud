package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.SensorDataCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDataDO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class SensorDataCoConvertor {
    private static final BeanCopier SENSOR_DATA__COPIER = BeanCopier.create(SensorDataDO.class, SensorDataCO.class,false);

    public static List<SensorDataCO> toClientObjectArray(List<SensorDataDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SensorDataCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static SensorDataCO toClientObject(SensorDataDO sensorData){
        SensorDataCO sensorDataCo = new SensorDataCO();
        SENSOR_DATA__COPIER.copy(sensorData,sensorDataCo,null);
        return sensorDataCo;
    }
}
