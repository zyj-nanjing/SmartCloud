package www.bwsensing.com.project.visualization.service.impl;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.mapper.StatisticsDataMapper;



/**
 * @author macos-zyj
 */
@Slf4j
@Service
@CatchAndLog
public class TimeSeriesDataServiceImpl implements ITimeSeriesDataService {
    @Resource
    private StatisticsDataMapper statisticsDataMapper;

    @Override
    public TimeSeriesDataCO getLastStatisticsData(String uniqueCode, String dataId) {
        try {
            StatisticsData statisticsData = statisticsDataMapper.getLastStatisticsData(uniqueCode, dataId);
            TimeSeriesDataCO clientObject = new TimeSeriesDataCO();
            clientObject.setTs(statisticsData.getTs());
            clientObject.setDataValue(statisticsData.getValue());
            return clientObject;
        } catch (Exception  ignore){
        }
        return new TimeSeriesDataCO();
    }
}
