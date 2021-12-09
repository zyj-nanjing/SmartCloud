package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.SensorCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
public class SensorCoConvertor {

    private static final BeanCopier SENSOR__COPIER = BeanCopier.create(SensorDO.class, SensorCO.class,false);


    public static List<SensorCO> toClientObjectArray(List<SensorDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SensorCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static SensorCO toClientObject(SensorDO sensorData){
        SensorCO sensorCo = new SensorCO();
        SENSOR__COPIER.copy(sensorData,sensorCo,null);
        return sensorCo;
    }

}
