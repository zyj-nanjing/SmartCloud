package www.bwsensing.com.device.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.tdengin.MonitorDataMapper;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.common.core.event.DomainEventPublisher;
import www.bwsensing.com.domainevent.DeviceDataComputationEvent;
import www.bwsensing.com.monitor.convertor.MonitorReceiveConvertor;
import www.bwsensing.com.domain.monitor.gateway.MonitorReceiveGateway;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;
import www.bwsensing.com.device.gatewayimpl.database.MonitorReceiveMapper;
import www.bwsensing.com.device.gatewayimpl.database.DeviceComputationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;


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
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private DeviceComputationMapper deviceComputationMapper;
    @Resource
    private DomainEventPublisher eventPublisher;
    /**
     *
     * @param receive
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void storageMonitorReceive(MonitorReceive receive,Boolean isDirect) {
        if(receive.getDataCollection().size()>0){
            monitorDataMapper.createSuperTable();
            monitorDataMapper.batchMonitorData(receive.getDataCollection());
            receive.setTotalSize(monitorDataMapper.queryMonitorDataSize(receive.getUniqueCode()));
            log.info("current sn:{} ----  Storage data  success --- data size:{}",receive.getUniqueCode(),receive.getTotalSize());
        }
        monitorReceiveMapper.saveReceive(MonitorReceiveConvertor.toDataObject(receive));
        ProductDeviceDO dataObject = productDeviceMapper.getProductDetailByUniqueCode(receive.getUniqueCode());
        if(null != dataObject&& isDirect){
           List<DeviceComputationDO> devicesComputations = deviceComputationMapper.queryDeviceComputationBySort(new DeviceComputationDO(dataObject.getId()));
            devicesComputations.forEach(deviceComputationDO -> {
                if (ComputationKind.SUBMIT_CALCULATION.getType().equals(deviceComputationDO.getComputationKind())){
                    DeviceDataComputationEvent<MonitorData> dataComputationEvent = new DeviceDataComputationEvent<>();
                    dataComputationEvent.setDeviceId(dataObject.getId());
                    dataComputationEvent.setDeviceComputationId(deviceComputationDO.getId());
                    dataComputationEvent.setDataCollections(receive.getDataCollection());
                    eventPublisher.publish(dataComputationEvent);
                }
            });
        }
    }

    private void batchStorageMonitor(List<MonitorData> dataCollection,String sn){
        log.info("current sn:{} ---- start to storage data --- data size:{}",sn,dataCollection.size());
        for (MonitorData monitorData : dataCollection){
            monitorDataMapper.createNewDataTable(monitorData);
            monitorDataMapper.insertMonitorData(monitorData);
        }
    }
}
