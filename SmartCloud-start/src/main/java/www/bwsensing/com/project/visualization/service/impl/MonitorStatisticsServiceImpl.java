package www.bwsensing.com.project.visualization.service.impl;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.Assert;
import org.springframework.stereotype.Service;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.project.visualization.domain.MonitorQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.domain.StatisticsResult;
import www.bwsensing.com.project.visualization.domain.status.MonitorQueryEnum;
import www.bwsensing.com.project.visualization.mapper.StatisticsDataMapper;
import www.bwsensing.com.project.visualization.service.IMonitorStatisticsService;
import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author macos-zyj
 */
@Slf4j
@Service
@CatchAndLog
public class MonitorStatisticsServiceImpl implements IMonitorStatisticsService {
    @Resource
    private StatisticsDataMapper monitorDataMapper;

    @Resource
    private MonitorItemsMapper monitorItemsMapper;

    @Override
    public SingleResponse<Map<String, StatisticsResult>> singleDataAnalyse(MonitorQuery query) {
        Map<String,List<StatisticsData>> dataMap = new LinkedHashMap<>(10);
        //获取排序完的时间戳并且封装查询数据
        List<Timestamp> sortedTimestamp = getSortTimestamp(dataMap,query);
        dataMap.forEach((key, statisticsData) -> {
            //各个数据补点
            dataMap.put(key,fixStatisticData(statisticsData,sortedTimestamp));
        });
        Map<String, StatisticsResult> resultData = new LinkedHashMap<>();
        List<MonitorItemsDO> parameters = getCurrentDeviceParam(query.getCurrentDevice());
        resultData.put("XAxis",packageAxis(sortedTimestamp));
        query.getParamIds().forEach(paramId->
                resultData.put(paramId,packageStaticData(getParameter(paramId,parameters),dataMap.get(paramId))));
        return SingleResponse.of(resultData);
    }


    private List<MonitorItemsDO> getCurrentDeviceParam(String sn){
        Assert.notNull(sn,"Sn编码不能为空");
        return monitorItemsMapper.selectItemsBySensorSn(sn);
    }
    /**
     * 组装数据
     * @return
     */
    private StatisticsResult packageAxis(List<Timestamp> sortTimestamp){
        StatisticsResult resultData = new StatisticsResult();
        resultData.setName("X轴数据");
        resultData.setTimestamp(sortTimestamp);
        return  resultData;
    }

    /**
     * 组装数据
     * @return
     */
    private StatisticsResult packageStaticData(MonitorItemsDO param,List<StatisticsData> dataCollection){
        StatisticsResult resultData = new StatisticsResult();
        if(null !=  param){
            resultData.setName(param.getItemName());
            resultData.setUnity(param.getUnit());
        }
        List<Float> dataResult = new ArrayList<>(dataCollection.size());
        dataCollection.forEach(data -> dataResult.add(data.getValue()));
        resultData.setData(dataResult);
        return  resultData;
    }

    private  MonitorItemsDO getParameter(String paramId,List<MonitorItemsDO> parameters){
        for (MonitorItemsDO paramParam:parameters){
            if (paramParam.getDataId().equals(paramId)){
                return paramParam;
            }
        }
        return null;
    }
    /**
     * 数据补点
     * @param dataCollection
     * @param sortedTimestamp
     */
    private List<StatisticsData> fixStatisticData(List<StatisticsData> dataCollection,List<Timestamp> sortedTimestamp){
        List<StatisticsData> fixedDataCollection = new ArrayList<>(dataCollection.size());
        AtomicInteger currentIndex = new AtomicInteger();
        sortedTimestamp.forEach(timestamp -> {
            AtomicBoolean dataExist = new AtomicBoolean(false);
            for (; currentIndex.get() <dataCollection.size();currentIndex.getAndIncrement()){
                StatisticsData current = dataCollection.get(currentIndex.get());
                if (current.getTs().equals(timestamp)){
                    fixedDataCollection.add(current);
                    dataExist.set(true);
                    break;
                }
                if(current.getTs().after(timestamp)){
                    break;
                }
            }
            if(!dataExist.get()){
                fixedDataCollection.add(new StatisticsData());
            }
        });
        return fixedDataCollection;
    }


    /**
     * 查询并封装待测数据
     * @param dataMap
     * @param query
     * @return
     */
    private List<Timestamp> getSortTimestamp(Map<String,List<StatisticsData>> dataMap,MonitorQuery query){
        List<Timestamp> timestampList = new ArrayList<>();
        List<String> paramList = query.getParamIds();
        //封装数据并初始化
        for (int index=0;index < paramList.size();index ++){
            MonitorItemsDO item = monitorItemsMapper.selectItemsByDataId(paramList.get(index));
            List<StatisticsData> statisticsDataList = queryDataCollection(query,index);
            List<Timestamp> timestamps = new ArrayList<>();
            statisticsDataList.forEach(data-> {
                timestamps.add(data.getTs());
                data.setValue(getDataValue(data.getValue(), item.getDecimalSize()));
            });
            dataMap.put(paramList.get(index),statisticsDataList);
            timestampList.addAll(timestamps);
        }
        timestampList = timestampList.stream().distinct().collect(Collectors.toList());
        // 获取对应的时间戳
        return timestampList.stream().sorted().collect(Collectors.toList());
    }



    private List<StatisticsData> queryDataCollection(MonitorQuery query, Integer index){
        try{
            query.setCode(query.getParamIds().get(index).replace(" ",""));
            switch (MonitorQueryEnum.queryResult(query.getDatatype())){
                case SPREAD:
                    return monitorDataMapper.listStatisticsSpreadData(query);
                default:
                    return monitorDataMapper.listStatisticsAvgData(query);
            }
        } catch (Exception exception){
            log.warn("Statistic data query error error msg:{}",exception.getLocalizedMessage());
            return new ArrayList<>();
        }
    }

    private  float getDataValue(Float dataValue,Integer decimalSize){
        int divisionValue = ((Double) Math.pow(10, decimalSize)).intValue();
        return (float)(Math.round(dataValue*divisionValue)*1.0/divisionValue);
    }
}
