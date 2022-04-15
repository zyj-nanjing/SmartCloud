package www.bwsensing.com.device.extension;

import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.cola.extension.Extension;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.device.dto.command.FacilityDataCmd;
import www.bwsensing.com.common.constant.HandlerScenarioCode;
import www.bwsensing.com.domain.device.gateway.SensorModelGateway;
import www.bwsensing.com.domain.device.model.data.MonitorReceive;
import www.bwsensing.com.device.dto.command.ClientMessageHandleCmd;
import www.bwsensing.com.device.extensionpoint.ClientMessageHandleExtPt;
import www.bwsensing.com.domain.device.model.data.model.ProductDataModel;
import www.bwsensing.com.domain.monitor.gateway.MonitorReceiveGateway;
import www.bwsensing.com.stream.object.DataMessage;
import www.bwsensing.com.stream.DataStreamReceive;
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

    @Resource
    private SensorModelGateway sensorModelGateway;

    @Resource
    private MonitorReceiveGateway monitorReceiveGateway;

    @Override
    public void getAndHandler(ClientMessageHandleCmd handleCmd) {
        DataStreamReceive streamData = JSONObject.parseObject(handleCmd.getJsonData(), DataStreamReceive.class);
        FacilityDataCmd dataCmd = initFacilityDataCmd(streamData.getReceiveData());
        dataCmd.setModelWebId(streamData.getModelId());
        List<ProductDataModel> dataModels = sensorModelGateway.getDataModelByWebId(streamData.getModelId());
        for (ProductDataModel model : dataModels){
            if(null!= model.getDataItems()&&model.getDataItems().size()>0){
                try {
                    MonitorReceive dataResult = model.messageAnalyse(dataCmd.getReceiveData(),dataCmd.getDataTimestamp());
                    StringBuilder receivedMessage = new StringBuilder();
                    dataCmd.getReceiveData().forEach(message->{
                        if (message.contains("\n")){
                            receivedMessage.append(message);
                        } else {
                            receivedMessage.append(message).append("\n");
                        }
                    });
                    dataResult.setChannelId(streamData.getUniqueCode());
                    dataResult.setIp(streamData.getSender());
                    dataResult.setReceiveMessage(receivedMessage.toString());
                    log.info("receiveMessage:{}", "\n"+receivedMessage);
                    dataResult.setReceiveTime(new Date());
                    monitorReceiveGateway.storageMonitorReceive(dataResult);
                    break;
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }

    private FacilityDataCmd initFacilityDataCmd(List<DataMessage> messageData){
        FacilityDataCmd  facilityDataCmd = new FacilityDataCmd();
        List<String> receiveData = new ArrayList<>();
        List<Timestamp> timestampResult = new ArrayList<>();
        for (DataMessage messageDatum : messageData) {
            receiveData.add(messageDatum.getMessage());
            timestampResult.add(messageDatum.getReceiveTime());
        }
        facilityDataCmd.setReceiveData(receiveData);
        facilityDataCmd.setDataTimestamp(timestampResult);
        return facilityDataCmd;
    }
}
