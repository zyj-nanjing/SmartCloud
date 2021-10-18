package www.bwsensing.com.convertor;

import www.bwsensing.com.domain.device.SensorHistory;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorHistoryDO;

/**
 * @author macos-zyj
 */
public class SensorHistoryConvertor {

    public static SensorHistory  doToDomain(SensorHistoryDO sensorHistoryDo){
        SensorHistory sensorHistory = new SensorHistory();
        sensorHistory.setId(sensorHistoryDo.getId());
        sensorHistory.setIpAddress(sensorHistoryDo.getIpAddress());
        sensorHistory.setChannelId(sensorHistoryDo.getChannelId());
        sensorHistory.setSnCode(sensorHistoryDo.getSnCode());
        sensorHistory.setOnlineTime(sensorHistoryDo.getOnlineTime());
        sensorHistory.setRelineTime(sensorHistoryDo.getRelineTime());
        sensorHistory.setOffLineTime(sensorHistoryDo.getOffLineTime());
        sensorHistory.setRefreshTime(sensorHistoryDo.getRefreshTime());
        return sensorHistory;
    }

    public static SensorHistoryDO domainToDo(SensorHistory sensorHistory){
        SensorHistoryDO sensorHistoryDo = new SensorHistoryDO();
        sensorHistoryDo.setId(sensorHistory.getId());
        sensorHistoryDo.setIpAddress(sensorHistory.getIpAddress());
        sensorHistoryDo.setChannelId(sensorHistory.getChannelId());
        sensorHistoryDo.setSnCode(sensorHistory.getSnCode());
        sensorHistoryDo.setOnlineTime(sensorHistory.getOnlineTime());
        sensorHistoryDo.setRelineTime(sensorHistory.getRelineTime());
        sensorHistoryDo.setOffLineTime(sensorHistory.getOffLineTime());
        sensorHistoryDo.setRefreshTime(sensorHistory.getRefreshTime());
        return sensorHistoryDo;
    }
}
