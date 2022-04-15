package www.bwsensing.com.project.visualization.service.impl;

import java.util.*;
import java.sql.Timestamp;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.alibaba.cola.catchlog.CatchAndLog;
import www.bwsensing.com.common.utills.DateUtils;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.device.dto.clientobject.SensorDynamicColumnCO;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.domain.StatisticsQuery;
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

    @Override
    public List<SensorDynamicColumnCO> getSensorDynamicColumns(List<String> dataIds, Date selectDate, String uniqueCode) {
        List<SensorDynamicColumnCO> columnCollection = new ArrayList<>();
        Map<String,List<StatisticsData>> dataMap = new LinkedHashMap<>(10);
        List<Timestamp> timestampList = new ArrayList<>();
        //封装数据并初始化
        for (String dataId : dataIds) {
            List<StatisticsData> statisticsDataList = getStatisticsData(dataId, selectDate, uniqueCode);
            List<Timestamp> timestamps = new ArrayList<>();
            statisticsDataList.forEach(data -> {
                timestamps.add(data.getTs());
                data.setValue(data.getValue());
            });
            dataMap.put(dataId, statisticsDataList);
            timestampList.addAll(timestamps);
        }
        timestampList = timestampList.stream().distinct().collect(Collectors.toList());
        for (String dataId : dataIds) {
            setColumnCollection(dataId,dataMap.get(dataId),timestampList,columnCollection);
        }
        return columnCollection;
    }


    /**
     * 数值计算
     * @param dataCollection
     * @param sortedTimestamp
     */
    private void setColumnCollection(String dataId,List<StatisticsData> dataCollection,List<Timestamp> sortedTimestamp,List<SensorDynamicColumnCO> columnCollection){
        AtomicInteger currentIndex = new AtomicInteger();
        AtomicInteger countIndex = new AtomicInteger();
        sortedTimestamp.forEach(timestamp -> {
            int index = countIndex.getAndIncrement();
            if(columnCollection.size()<=index){
                SensorDynamicColumnCO columnCo = new SensorDynamicColumnCO();
                columnCo.setDataMap(new LinkedHashMap<>());
                columnCollection.add(columnCo);
            }
            AtomicBoolean dataExist = new AtomicBoolean(false);
            for (; currentIndex.get() <dataCollection.size();currentIndex.getAndIncrement()){
                StatisticsData current = dataCollection.get(currentIndex.get());
                if (current.getTs().equals(timestamp)){
                    columnCollection.get(index).getDataMap().put(dataId,toTimeSeriesData(current));
                    dataExist.set(true);
                    break;
                }
                if(current.getTs().after(timestamp)){
                    break;
                }
            }
            if(!dataExist.get()){
                TimeSeriesDataCO dataCo = new TimeSeriesDataCO();
                dataCo.setTs(timestamp);
                dataCo.setDataValue(null);
                columnCollection.get(index).getDataMap().put(dataId,dataCo);
            }
        });

    }

    private TimeSeriesDataCO toTimeSeriesData(StatisticsData statisticsData){
        TimeSeriesDataCO record = new TimeSeriesDataCO();
        record.setTs(statisticsData.getTs());
        record.setDataValue(statisticsData.getValue());
        return record;
    }

    private List<StatisticsData> getStatisticsData(String dataId,Date selectDate, String uniqueCode){
        try {
            StatisticsQuery query = new StatisticsQuery();
            query.setDataId(dataId);
            query.setUniqueCode(uniqueCode);
            query.setStartTime(new Timestamp(DateUtils.getStartTime(selectDate,0).getTime()));
            query.setEndTime(new Timestamp(DateUtils.getEndTime(selectDate,0).getTime()));
            return statisticsDataMapper.listStatisticsCollection(query);
        } catch (Exception  ignore){

        }
        return new ArrayList<>();
    }
}
