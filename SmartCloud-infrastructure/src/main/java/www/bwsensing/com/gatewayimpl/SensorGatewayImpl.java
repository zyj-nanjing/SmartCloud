package www.bwsensing.com.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.SensorConvertor;
import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.domain.gateway.SensorGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SensorGatewayImpl implements SensorGateway {
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public void saveSensor(SensorInfo sensorInfo) {

    }

    @Override
    public void updateSensor(SensorInfo sensorInfo) {

    }

    @Override
    public void updateSensors(List<SensorInfo> sensors) {
        sensors.forEach(sensor -> sensorMapper.update(SensorConvertor.toDataObject(sensor)));
    }

    @Override
    public void deleteSensor(Integer id) {

    }

    @Override
    public List<SensorInfo> selectSensorByCurrentGroup() {
        return null;
    }

    @Override
    public List<SensorInfo> getSensorsByMonitorStructure(Integer structureId) {
        List<SensorDO> sensorDataCollection = sensorMapper.getSensorsByMonitorStructure(structureId);
        return SensorConvertor.toDomainCollection(sensorDataCollection);
    }

    @Override
    public SensorInfo getSensorInfoById(Integer sensorId) {
        SensorDO sensorDo = sensorMapper.selectSensorById(sensorId);
        if(null != sensorDo){
            if (sensorDo.getMemberGroupId().equals(tokenGateway.getTokenInfo().getGroupId())){
                return SensorConvertor.toDomain(sensorDo);
            } else {
                throw new BizException("THIS_SENSOR_NOT_PERMISSION","无权选择该传感器");
            }
        }
        throw new BizException("SENSOR_NOT_FOUND","传感器不存在");
    }
}
