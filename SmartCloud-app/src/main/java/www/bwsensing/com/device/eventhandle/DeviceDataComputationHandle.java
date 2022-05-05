package www.bwsensing.com.device.eventhandle;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.device.gatewayimpl.database.DeviceComputationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;
import www.bwsensing.com.domain.monitor.gateway.MonitorReceiveGateway;
import www.bwsensing.com.domainevent.DeviceDataComputationEvent;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class DeviceDataComputationHandle implements EventHandlerI<Response, DeviceDataComputationEvent<MonitorData>> {
    @Resource
    private DeviceComputationMapper deviceComputationMapper;
    @Resource
    private ProductModelGateway productModelGateway;
    @Resource
    private ProductDeviceGateway productDeviceGateway;
    @Resource
    private MonitorReceiveGateway monitorReceiveGateway;

    /**
     * @// TODO: 2022/5/5 添加非上报触发计算模型的交互逻辑 以及 设置对应的缓存机制
     * @param deviceDataComputationEvent
     * @return
     */
    @Override
    public Response execute(DeviceDataComputationEvent<MonitorData> deviceDataComputationEvent) {
        if(null != deviceDataComputationEvent.getDataCollections()){
            DeviceComputationDO deviceComputation = deviceComputationMapper.getDeviceComputationById(deviceDataComputationEvent.getDeviceComputationId());
            DataComputationModel computationModel = productModelGateway.getDataComputationModelById(deviceComputation.getDataComputationId());
            ProductDevice device = productDeviceGateway.getDeviceDetailById(deviceDataComputationEvent.getDeviceId());
            computationModel.setExtraProductDataItems(device.getExtraProductData());
            computationModel.setComputationKind(ComputationKind.SUBMIT_CALCULATION);
            List<MonitorData> dataCollection =  computationModel.computationDataCollection(device.getUniqueCode(),deviceDataComputationEvent.getDataCollections());
            saveComputationData(device.getUniqueCode(),dataCollection);
        }
        return Response.buildSuccess();
    }

    private void saveComputationData(String uniqueCode, List<MonitorData> dataCollection){
        MonitorReceive dataReceive = new MonitorReceive();
        dataReceive.setUniqueCode(uniqueCode);
        dataReceive.setDataCollection(dataCollection);
        monitorReceiveGateway.storageMonitorReceive(dataReceive,false);
    }

}
