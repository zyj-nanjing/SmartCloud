package www.bwsensing.com.device.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.utills.DateUtils;
import www.bwsensing.com.device.api.SensorDataService;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.device.command.query.SensorDataSortQueryExo;
import www.bwsensing.com.device.dto.clientobject.SensorDynamicTableCO;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorItemsDO;
import static www.bwsensing.com.common.convertor.DataItemsCoConvertor.toDataItemsCo;
import www.bwsensing.com.device.dto.command.query.SensorTableQuery;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.domain.system.model.user.SystemUser;


/**
 * @author macos-zyj
 */
@Service
public class ISensorDataServiceImpl implements SensorDataService {

    @Resource
    private SensorDataSortQueryExo sensorDataSortQueryExo;

    @Resource
    private TokenGateway tokenGateway;

    @Resource
    private SystemUserGateway userGateway;

    @Resource
    private SensorMapper sensorMapper;

    @Resource
    private MonitorItemsMapper itemsMapper;

    @Resource
    private RedisService redisService;


    @Resource
    private ITimeSeriesDataService timeSeriesDataService;

    @Override

    public PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery) {
        return sensorDataSortQueryExo.execute(dataSortQuery);
    }

    @Override
    public SingleResponse<SensorDynamicTableCO> sensorDynamicTableQuery(SensorTableQuery tableQuery) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        AtomicBoolean isAllow = new AtomicBoolean(false);
        List<SensorDO> sensorList = sensorMapper.selectSensorByGroupId(systemUser.getGroupId());
        AtomicReference<SensorDO> currentSensor = new AtomicReference<>();
        sensorList.forEach(sensorDO -> {
            if (sensorDO.getSn().equals(tableQuery.getUniqueCode())){
                isAllow.set(true);
                currentSensor.set(sensorDO);
            }
        });
        if(isAllow.get()){
            String checkKey = getRedisCheckKey(tableQuery);
            SensorDynamicTableCO result = redisService.getCacheObject(checkKey);
            if (null == result || !tableQuery.getSelectDate().before(DateUtils.getStartTime(new Date(),0)))
            {
                SensorDynamicTableCO finalResult = new SensorDynamicTableCO();
                finalResult.setDataItems(new ArrayList<>());
                finalResult.setColumns(new ArrayList<>());
                int modelId = currentSensor.get().getModelId();
                List<MonitorItemsDO> itemData = itemsMapper.selectItemsByModelId(modelId);
                List<String> dataIds = new ArrayList<>();
                itemData.forEach(item ->{
                    finalResult.getDataItems().add(toDataItemsCo(item));
                    dataIds.add(item.getDataId());
                });
                finalResult.setColumns(timeSeriesDataService.getSensorDynamicColumns(dataIds,tableQuery.getSelectDate(),tableQuery.getUniqueCode()));
                if (tableQuery.getSelectDate().before(DateUtils.getStartTime(new Date(),0))){
                    redisService.setCacheObject(checkKey,finalResult,15L, TimeUnit.DAYS);
                }
                return SingleResponse.of(finalResult);
            } else {
                return SingleResponse.of(result);
            }
        }
        return SingleResponse.of(new SensorDynamicTableCO());
    }

    private  String getRedisCheckKey(SensorTableQuery query){
        String prefix = "SENSOR_DYNAMIC_";
        return prefix + query.getUniqueCode()+"_"+query.getSelectDate();
    }
}
