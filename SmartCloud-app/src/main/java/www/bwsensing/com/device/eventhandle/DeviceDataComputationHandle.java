package www.bwsensing.com.device.eventhandle;

import cn.hutool.core.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.utills.CronDateUtils;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.domain.device.gateway.DeviceSeriesDataGateway;
import www.bwsensing.com.domain.device.model.ComputationHandleKind;
import www.bwsensing.com.domain.device.model.ProductDataItem;
import www.bwsensing.com.domain.device.model.ProductDevice;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.domain.device.gateway.ProductModelGateway;
import www.bwsensing.com.domain.device.gateway.ProductDeviceGateway;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;
import www.bwsensing.com.device.gatewayimpl.database.DeviceComputationMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import www.bwsensing.com.domain.device.model.data.model.DataComputationModel;
import www.bwsensing.com.domain.monitor.gateway.MonitorReceiveGateway;
import www.bwsensing.com.domainevent.DeviceDataComputationEvent;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Timestamp;
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
    @Resource
    private ITimeSeriesDataService timeSeriesDataService;
    @Resource
    private DeviceSeriesDataGateway deviceSeriesDataGateway;

    /**
     * @// TODO: 2022/5/5 添加非上报触发计算模型的交互逻辑 以及 设置对应的缓存机制
     * @param deviceDataComputationEvent
     * @return
     */
    @Override
    public Response execute(DeviceDataComputationEvent<MonitorData> deviceDataComputationEvent) {
        DeviceComputationDO deviceComputation = deviceComputationMapper.getDeviceComputationById(deviceDataComputationEvent.getDeviceComputationId());
        DataComputationModel computationModel = productModelGateway.getDataComputationModelById(deviceComputation.getDataComputationId());
        ProductDevice device = productDeviceGateway.getDeviceDetailById(deviceDataComputationEvent.getDeviceId());
        computationModel.setExtraProductDataItems(device.getExtraProductData());
        List<MonitorData> dataCollection = new ArrayList<>();
        if(null != deviceDataComputationEvent.getDataCollections() && deviceDataComputationEvent.getDataCollections().size() > 0){
            computationModel.setComputationKind(ComputationKind.SUBMIT_CALCULATION);
            dataCollection .addAll(computationModel.computationDataCollection(device.getUniqueCode(),deviceDataComputationEvent.getDataCollections()));
            log.info("device computation unique code:{}, name:{}, type:{}",device.getUniqueCode(),computationModel.getFormulaName(),"上报触发");
        } else {
            if(deviceComputation.getHandleKind().equals(ComputationHandleKind.LATEST_DATA.getValue())){
                computationModel.setComputationKind(ComputationKind.SCHEDULED_CALCULATION);
                dataCollection .addAll(computationModel.computationDataCollection(device.getUniqueCode(),getLastStatisticsDataCollection(computationModel.getProductDataItems(),device.getUniqueCode())));
                log.info("device computation unique code:{}, name:{}, type:{}",device.getUniqueCode(),computationModel.getFormulaName(),"定时触发-最新数据");
            } else {
                // 注获取触发时间会有延迟 因此第一次获得的上一次触发时间为结束时间再获取一次才是上一次的触发时间!
                LocalDateTime triggerTime = CronDateUtils.lastExecution(deviceComputation.getCronExpression(),deviceDataComputationEvent.getTriggerTime());
                LocalDateTime beforeTime = CronDateUtils.lastExecution(deviceComputation.getCronExpression(),triggerTime);
                Timestamp startTime = Timestamp.valueOf(beforeTime);
                Timestamp endTime = Timestamp.valueOf(triggerTime);
                computationModel.setComputationKind(ComputationKind.SCHEDULED_CALCULATION);
                dataCollection .addAll(computationModel.computationDataCollection(device.getUniqueCode(),
                        getStatisticsDataCollectionWithFunction(computationModel.getProductDataItems(),device.getUniqueCode(),deviceComputation.getFunctionCode(), startTime,endTime)));
                log.info("device computation unique code:{}, name:{}, type:{}",device.getUniqueCode(),computationModel.getFormulaName(),"定时触发-函数数据");
            }
        }
        saveComputationData(device.getUniqueCode(),dataCollection);
        return Response.buildSuccess();
    }

    private List<MonitorData> getLastStatisticsDataCollection(List<ProductDataItem>  productDataItems,String uniqueCode){
        List<MonitorData> resultCollection = new ArrayList<>(productDataItems.size());
        Assert.notEmpty(productDataItems,"数据监测项不能为空!");
        for(ProductDataItem dataItem : productDataItems){
            TimeSeriesDataCO currentData = timeSeriesDataService.getLastStatisticsData(uniqueCode, dataItem.getDataId());
            String currentValue = String.valueOf(currentData.getDataValue());
            resultCollection.add(new MonitorData(dataItem.getDataId(),Double.parseDouble(String.valueOf(currentValue)),uniqueCode));
        }
        return resultCollection;
    }

    private List<MonitorData> getStatisticsDataCollectionWithFunction(List<ProductDataItem>  productDataItems, String uniqueCode, Integer functionCode, Timestamp startTime, Timestamp endTime){
        List<MonitorData> resultCollection = new ArrayList<>(productDataItems.size());
        Assert.notEmpty(productDataItems,"数据监测项不能为空!");
        Assert.notNull(functionCode,"处理函数编号不能为空!");
        for(ProductDataItem dataItem : productDataItems){
            switch(functionCode){
                case 2:
                    resultCollection.addAll(deviceSeriesDataGateway.getDeviceSeriesDataWithMax(uniqueCode,dataItem.getDataId(),startTime,endTime));
                    break;
                case 3:
                    resultCollection.addAll(deviceSeriesDataGateway.getDeviceSeriesDataWithMin(uniqueCode,dataItem.getDataId(),startTime,endTime));
                    break;
                default:
                    resultCollection.addAll(deviceSeriesDataGateway.getDeviceSeriesDataWithAvg(uniqueCode,dataItem.getDataId(),startTime,endTime));
                    break;

            }
        }
        return resultCollection;
    }

    private void saveComputationData(String uniqueCode, List<MonitorData> dataCollection){
        MonitorReceive dataReceive = new MonitorReceive();
        dataReceive.setUniqueCode(uniqueCode);
        dataReceive.setDataCollection(dataCollection);
        monitorReceiveGateway.storageMonitorReceive(dataReceive,false);
    }

}
