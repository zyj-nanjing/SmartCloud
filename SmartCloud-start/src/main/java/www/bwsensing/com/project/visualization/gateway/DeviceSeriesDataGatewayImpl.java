package www.bwsensing.com.project.visualization.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.device.model.data.MonitorData;
import www.bwsensing.com.domain.device.gateway.DeviceSeriesDataGateway;
import www.bwsensing.com.project.visualization.domain.DeviceDataQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.mapper.StatisticsDataMapper;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class DeviceSeriesDataGatewayImpl implements DeviceSeriesDataGateway {
    @Resource
    private StatisticsDataMapper statisticsDataMapper;

    @Override
    public List<MonitorData> getDeviceSeriesDataWithAvg(String uniqueCode, String dataId, Timestamp startTime, Timestamp endTime) {
        List<StatisticsData> dataCollection = statisticsDataMapper.listStatisticsAvgData(initDataQuery(uniqueCode, dataId, startTime, endTime));
        return toMonitorDataCollection(dataCollection,dataId,uniqueCode);
    }

    @Override
    public List<MonitorData> getDeviceSeriesDataWithSpread(String uniqueCode, String dataId, Timestamp startTime, Timestamp endTime) {
        List<StatisticsData> dataCollection = statisticsDataMapper.listStatisticsSpreadData(initDataQuery(uniqueCode, dataId, startTime, endTime));
        return toMonitorDataCollection(dataCollection,dataId,uniqueCode);
    }

    @Override
    public List<MonitorData> getDeviceSeriesDataWithMax(String uniqueCode, String dataId, Timestamp startTime, Timestamp endTime) {
        List<StatisticsData> dataCollection = statisticsDataMapper.listStatisticsMaxData(initDataQuery(uniqueCode, dataId, startTime, endTime));
        return toMonitorDataCollection(dataCollection,dataId,uniqueCode);
    }

    @Override
    public List<MonitorData> getDeviceSeriesDataWithMin(String uniqueCode, String dataId, Timestamp startTime, Timestamp endTime) {
        List<StatisticsData> dataCollection = statisticsDataMapper.listStatisticsMinData(initDataQuery(uniqueCode, dataId, startTime, endTime));
        return toMonitorDataCollection(dataCollection,dataId,uniqueCode);
    }

    private DeviceDataQuery initDataQuery(String uniqueCode, String dataId, Timestamp startTime, Timestamp endTime){
        DeviceDataQuery query = new DeviceDataQuery();
        query.setCurrentDevice(uniqueCode);
        query.setCode(dataId);
        query.setStartTime(startTime);
        query.setEndTime(endTime);
        return query;
    }

    private List<MonitorData> toMonitorDataCollection(List<StatisticsData> dataCollection,String dataId,String uniqueCode){
        List<MonitorData> result = new ArrayList<>();
        if (null !=dataCollection && dataCollection.size() > 0){
            dataCollection.forEach(current->{
                result.add(toMonitorData(current, dataId,uniqueCode));
            });
        }
        return result;
    }

    private MonitorData toMonitorData(StatisticsData statisticsData,String dataId,String uniqueCode){
        MonitorData result = new MonitorData();
        result.setDataIdValue(statisticsData.getValue());
        result.setTimeStamp(statisticsData.getTs());
        result.setDataId(dataId);
        result.setUniqueCode(uniqueCode);
        return result;
    }
}
