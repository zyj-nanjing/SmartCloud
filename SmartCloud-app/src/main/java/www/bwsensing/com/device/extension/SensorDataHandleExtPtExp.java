package www.bwsensing.com.device.extension;

import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.cola.extension.Extension;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.constant.HandlerScenarioCode;
import www.bwsensing.com.device.dto.command.ClientMessageHandleCmd;
import www.bwsensing.com.device.extensionpoint.ClientMessageHandleExtPt;
import www.bwsensing.com.device.dto.command.FacilityDataCmd;
import www.bwsensing.com.domain.device.gateway.SensorModelGateway;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;
import www.bwsensing.com.domain.monitor.gateway.MonitorReceiveGateway;
import www.bwsensing.com.stream.DataStreamReceive;
import www.bwsensing.com.stream.object.DataMessage;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.CLIENT_STREAM_MESSAGE,scenario = HandlerScenarioCode.DATA_MESSAGE)
public class SensorDataHandleExtPtExp implements ClientMessageHandleExtPt {
    private static final String CHANGE_LINE = "\n";

    @Resource
    private SensorModelGateway sensorModelGateway;

    @Resource
    private MonitorReceiveGateway monitorReceiveGateway;

    @Override
    public void getAndHandler(ClientMessageHandleCmd handleCmd) {
        DataStreamReceive streamData = JSONObject.parseObject(handleCmd.getJsonData(), DataStreamReceive.class);
        String dataMessage = getReceiveMessage(streamData.getReceiveData());
        List<Timestamp> receiveTimestamp = getReceiveTimestamp(streamData.getReceiveData());
        FacilityDataCmd dataCmd = initFacilityDataCmd(dataMessage,receiveTimestamp);
        dataCmd.setModelWebId(streamData.getModelId());
        List<ProductDataModel> dataModels = sensorModelGateway.getDataModelByWebId(streamData.getModelId());
        for (ProductDataModel model : dataModels){
            if(null!= model.getDataItems()&&model.getDataItems().size()>0){
                try {
                    MonitorReceive dataResult = model.messageAnalyse(dataCmd.getReceiveData(),dataCmd.getDataTimestamp());
                    StringBuilder receivedMessage = new StringBuilder();
                    dataCmd.getReceiveData().forEach(message -> receivedMessage.append(message).append("\n"));
                    dataResult.setChannelId(streamData.getUniqueCode());
                    dataResult.setIp(streamData.getSender());
                    dataResult.setReceiveMessage(receivedMessage.toString());
                    dataResult.setReceiveTime(new Date());
                    monitorReceiveGateway.storageMonitorReceive(dataResult);
                    break;
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }



    private String getReceiveMessage(List<DataMessage> messageData){
        StringBuilder logMessage = new StringBuilder();
        messageData.forEach(message -> logMessage.append(message.getMessage()).append(CHANGE_LINE));
        return logMessage.toString();
    }

    private List<Timestamp> getReceiveTimestamp(List<DataMessage> messageData){
        List<Timestamp> timestamps = new ArrayList<>();
        messageData.forEach(message ->timestamps.add(message.getReceiveTime()));
        return timestamps;
    }

    private FacilityDataCmd initFacilityDataCmd(String receiveMessage, List<Timestamp> timestamps){
        FacilityDataCmd  facilityDataCmd = new FacilityDataCmd();
        String [] atrArray =  receiveMessage.split(CHANGE_LINE);
        List<String> receiveData = new ArrayList<>();
        List<Timestamp> timestampResult = new ArrayList<>();
        boolean isMix = atrArray.length > timestamps.size();
        if (atrArray.length > 0){
            for (int i =0;i<atrArray.length;i++){
                receiveData.add(atrArray[i]);
                if (!isMix){
                    timestampResult.add(timestamps.get(i));
                } else{
                    int timeIndex = Math.min((i / 2), timestamps.size() - 1);
                    Timestamp currentTime = timestamps.get(timeIndex);
                    if (((i+1)%2)==0) {
                        timestampResult.add(new Timestamp(currentTime.getTime() + (long) 10));
                    } else {
                        timestampResult.add(currentTime);
                    }
                }
            }
        }
        facilityDataCmd.setReceiveData(receiveData);
        facilityDataCmd.setDataTimestamp(timestampResult);
        return facilityDataCmd;
    }
}
