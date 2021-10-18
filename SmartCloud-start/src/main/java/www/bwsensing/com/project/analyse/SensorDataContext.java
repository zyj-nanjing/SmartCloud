package www.bwsensing.com.project.analyse;


import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.SensorUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.device.data.MonitorData;
import www.bwsensing.com.domain.device.data.MonitorReceive;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.project.analyse.domain.SensorData;
import www.bwsensing.com.project.analyse.state.SensorAnalyseState;
import javax.annotation.Resource;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 传感器策略分析
 * @author macos-zyj
 */
@Slf4j
@Component
public class SensorDataContext {
    private static final String ASCII_SPLIT = ",";

    @Resource
    private SensorMapper sensorManager;

     public MonitorReceive analyseRawData(String totalMessage){
         List<String> receiveData = splitReceiveCollection(totalMessage);
         String sn = getSnCode(receiveData);
         return initMonitorReceive(sn,receiveData);
     }

     private MonitorReceive initMonitorReceive(String sn,List<String> receiveData){
         SensorBaseState analyseState = validateRawGetState(sn);
         MonitorReceive monitorReceive = new MonitorReceive();
         monitorReceive.setSn(sn);
         monitorReceive.setDataCollection(new ArrayList<>());
         if(receiveData.size() > 0){
             for (String rawData: receiveData) {
                 log.info(rawData);
                 try {
                     SensorData result = analyseState.analyseRawData(rawData);
                     monitorReceive.getDataCollection().addAll(result.toSeriesData());
                     monitorReceive.setPhoneNumber(result.getPhoneNumber());
                     monitorReceive.setTemperature(result.getTemperature());
                     monitorReceive.setElectricity(result.getElectricity());
                 } catch (Exception e){
                     log.warn("analyse error Raw:{} error msg:{}",rawData,e.getLocalizedMessage());
                 }
             }
             monitorReceive.setSendCount(monitorReceive.getDataCollection().size());
         }
         monitorReceive.setReceiveSize(receiveData.size());
         return monitorReceive;
     }

    private List<String> splitReceiveCollection(String raw){
        String [] atrArray =  raw.split("\n");
        return  Arrays.stream(atrArray).filter(string -> !string.isEmpty()).collect(Collectors.toList());
    }

    private String getSnCode(List<String> dataCollection){
        return analyseSensorSn(dataCollection.get(0));
    }

    private SensorBaseState validateRawGetState(String sn){
        String analyseFunction = sensorManager.queryAnalyseFunctionBySn(sn);
        if (StringUtils.isNotEmpty(analyseFunction)){
            return SensorAnalyseState.getSensorState(analyseFunction);
        }else {
            throw new BizException("SENSOR_DATA_ANALYSE_FUNCTION_NOT_FOUND","传感器数据解析算法不存在");
        }
    }



     private String analyseSensorSn(String sensorRaw){
         if (!sensorRaw.contains(ASCII_SPLIT)){
             String currentBitCode = sensorRaw.substring(0, 2);
             return SensorUtils.hexSnAnalyse(currentBitCode);
         } else{
             String[] dataArray = sensorRaw.split(",");
             return SensorUtils.asciiSnAnalyse(dataArray[0]);
         }
     }
}
