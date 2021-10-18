package www.bwsensing.com.gatewayimpl;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.convertor.MonitorReceiveConvertor;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.domain.gateway.MonitorReceiveGateway;
import www.bwsensing.com.gatewayimpl.database.MonitorReceiveMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;
import www.bwsensing.com.gatewayimpl.tdengin.MonitorDataMapper;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class MonitorReceiveGatewayImpl implements MonitorReceiveGateway {
    @Resource
    private MonitorDataMapper monitorDataMapper;
    @Resource
    private MonitorReceiveMapper monitorReceiveMapper;
    @Resource
    private SensorMapper sensorMapper;

    /**
     * @param receive
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void storageMonitorReceive(MonitorReceive receive) {
        if(receive.getDataCollection().size()>0){
            monitorDataMapper.createSuperTable();
            monitorDataMapper.batchMonitorData(receive.getDataCollection());
            receive.setTotalSize(monitorDataMapper.queryMonitorDataSize(receive.getSn()));
        }
        monitorReceiveMapper.saveReceive(MonitorReceiveConvertor.toDataObject(receive));
        updateSensor(receive);
    }

    private void updateSensor(MonitorReceive receive) {
        SensorDO dataObject = sensorMapper.selectSensorBySn(receive.getSn());
        dataObject.setElectricity(receive.getElectricity());
        dataObject.setPhoneNumber(receive.getPhoneNumber());
        dataObject.setTemperature(receive.getTemperature());
        if (null == dataObject.getFirstOnlineTime()) {
            dataObject.setFirstOnlineTime(receive.getReceiveTime());
        }
        if (null == dataObject.getTotalSendSize()) {
            dataObject.setTotalSendSize(receive.getSendCount());
        } else{
            dataObject.setTotalSendSize(dataObject.getTotalSendSize()+receive.getSendCount());
        }
        dataObject.setLastSendAddress(receive.getIp());
        dataObject.setLastSendTime(receive.getReceiveTime());
        dataObject.setLastSendSize(receive.getReceiveSize());
        dataObject.setLastSendCount(receive.getSendCount());
        dataObject.setTotalSize(receive.getTotalSize());
        sensorMapper.update(dataObject);
    }
}
