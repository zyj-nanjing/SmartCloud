package www.bwsensing.com.eventhandle;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.utills.SensorUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.domain.gateway.MonitorReceiveGateway;
import www.bwsensing.com.domainevent.FacilityDataReceiveEvent;
import www.bwsensing.com.domainevent.object.DataMessage;
import www.bwsensing.com.dto.command.FacilityDataCmd;
import www.bwsensing.com.extensionpoint.FacilityDataAnalyseExtPt;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class FacilityDataReceiveHandle implements EventHandlerI<Response, FacilityDataReceiveEvent> {
    private static final String CHANGE_LINE = "\n";
    @Resource
    private ExtensionExecutor extensionExecutor;

    @Autowired
    private MonitorReceiveGateway sensorDataGateway;

    @Resource
    private SensorMapper sensorManager;


    @Override
    public Response execute(FacilityDataReceiveEvent receiveEvent) {
        String dataMessage = getReceiveMessage(receiveEvent.getReceiveData());
        List<Timestamp> receiveTimestamp = getReceiveTimestamp(receiveEvent.getReceiveData());
        FacilityDataCmd  facilityDataCmd = initFacilityDataCmd(dataMessage,receiveTimestamp);
        MonitorReceive receiveResult = extensionExecutor.execute(FacilityDataAnalyseExtPt.class, facilityDataCmd.getBizScenario(),
                extension -> extension.analyseFacilityData(facilityDataCmd));
        log.info("Start to analyse and storage received data SensorSn:{}",receiveResult.getSn());
        setSendMessageDetail(receiveEvent,receiveResult);
        sensorDataGateway.storageMonitorReceive(receiveResult);
        return Response.buildSuccess();
    }

    private void setSendMessageDetail(FacilityDataReceiveEvent receiveEvent,MonitorReceive receiveResult) {
        receiveResult.setChannelId(receiveEvent.getChannelId());
        receiveResult.setIp(receiveEvent.getIp());
        receiveResult.setReceiveMessage(getReceiveMessage(receiveEvent.getReceiveData()));
        receiveResult.setReceiveTime(receiveEvent.getReceiveTime());
    }

    private String getReceiveMessage(List<DataMessage> messageData){
        StringBuilder logMessage = new StringBuilder();
        messageData.forEach(message ->logMessage.append(message.getMessage()));
        return logMessage.toString();
    }
    private List<Timestamp> getReceiveTimestamp(List<DataMessage> messageData){
        List<Timestamp> timestamps = new ArrayList<>();
        messageData.forEach(message ->timestamps.add(message.getReceiveTime()));
        return timestamps;
    }

    private FacilityDataCmd initFacilityDataCmd(String receiveMessage,List<Timestamp> timestamps){
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
                    int timeIndex = i!= 0 ? i/2:0;
                    Timestamp currentTime = timestamps.get(timeIndex);
                    if (((i+1)%2)==0) {
                        timestampResult.add(new Timestamp(currentTime.getTime() + (long) 10));
                    } else {
                        timestampResult.add(currentTime);
                    }
                }
            }
        }
        String sensorSn = SensorUtils.getSnCode(receiveData);
        facilityDataCmd.setSn(sensorSn);
        facilityDataCmd.setReceiveData(receiveData);
        facilityDataCmd.setDataTimestamp(timestampResult);
        facilityDataCmd.setBizScenario(getCorrectScenario(sensorSn));
        facilityDataCmd.setAnalyseKind(facilityDataCmd.getBizScenario().getUniqueIdentity());
        return facilityDataCmd;
    }

    private BizScenario getCorrectScenario(String sn){
        String analyseFunction = sensorManager.queryAnalyseFunctionBySn(sn);
        if (StringUtils.isNotEmpty(analyseFunction)){
            return BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, BizScenarioCode.USER_CAUSE_ANALYSE,analyseFunction);
        }else {
            throw new BizException("SENSOR_DATA_ANALYSE_FUNCTION_NOT_FOUND","传感器数据解析算法不存在");
        }
    }

}
