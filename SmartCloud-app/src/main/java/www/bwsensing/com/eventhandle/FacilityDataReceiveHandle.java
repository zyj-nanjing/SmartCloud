package www.bwsensing.com.eventhandle;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.stream.Collectors;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.common.utills.SensorUtils;
import www.bwsensing.com.common.utills.StringUtils;
import com.alibaba.cola.extension.ExtensionExecutor;
import www.bwsensing.com.domainevent.object.DataMessage;
import www.bwsensing.com.dto.command.FacilityDataCmd;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.domain.gateway.MonitorReceiveGateway;
import www.bwsensing.com.domainevent.FacilityDataReceiveEvent;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.extensionpoint.FacilityDataAnalyseExtPt;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;

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
        FacilityDataCmd  facilityDataCmd = initFacilityDataCmd(receiveEvent.getReceiveData());
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
        receiveResult.setReceiveMessage(getReceiveMessageLog(receiveEvent.getReceiveData()));
        receiveResult.setReceiveTime(receiveEvent.getReceiveTime());
    }

    private String getReceiveMessageLog(List<DataMessage> messageData){
        StringBuilder logMessage = new StringBuilder();
        messageData.forEach(message ->logMessage.append(message.getMessage()) );
        return logMessage.toString();
    }

    private FacilityDataCmd initFacilityDataCmd(List<DataMessage> messageData){
        FacilityDataCmd  facilityDataCmd = new FacilityDataCmd();
        List<Timestamp> timestamps = new ArrayList<>();
        List<String> receiveData = new ArrayList<>();
        messageData.forEach(message ->{
           if (message.getMessage().contains(CHANGE_LINE)){
               String [] atrArray =  message.getMessage().split(CHANGE_LINE);
               Timestamp currentTime = message.getReceiveTime();
               if (atrArray.length > 0){
                   for (String s : atrArray) {
                       timestamps.add(currentTime);
                       receiveData.add(s);
                       currentTime = new Timestamp(currentTime.getTime() + (long) 10);
                   }
               }
           } else {
               timestamps.add(message.getReceiveTime());
               receiveData.add(message.getMessage());
           }
        });
        String sensorSn = SensorUtils.getSnCode(receiveData);
        facilityDataCmd.setSn(sensorSn);
        facilityDataCmd.setReceiveData(receiveData);
        facilityDataCmd.setDataTimestamp(timestamps);
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
