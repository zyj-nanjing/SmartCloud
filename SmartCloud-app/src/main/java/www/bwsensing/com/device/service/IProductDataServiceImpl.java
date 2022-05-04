package www.bwsensing.com.device.service;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.Resource;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.utills.DateUtils;
import www.bwsensing.com.device.api.ProductDataService;
import www.bwsensing.com.common.api.ITimeSeriesDataService;
import www.bwsensing.com.device.dto.clientobject.ProductDynamicTableCO;
import www.bwsensing.com.device.gatewayimpl.database.ProductDataItemMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDataItemDO;
import static www.bwsensing.com.common.convertor.DataItemsCoConvertor.toClientObject;
import www.bwsensing.com.device.dto.command.query.ProductDataTableQuery;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.domain.system.model.user.SystemUser;


/**
 * @author macos-zyj
 */
@Service
public class IProductDataServiceImpl implements ProductDataService {

    @Resource
    private TokenGateway tokenGateway;

    @Resource
    private SystemUserGateway userGateway;

    @Resource
    private ProductDeviceMapper productDeviceMapper;

    @Resource
    private ProductDataItemMapper itemsMapper;

    @Resource
    private RedisService redisService;


    @Resource
    private ITimeSeriesDataService timeSeriesDataService;

    @Override
    public SingleResponse<ProductDynamicTableCO> productDynamicTableQuery(ProductDataTableQuery tableQuery) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        AtomicBoolean isAllow = new AtomicBoolean(false);
        List<ProductDeviceDO> sensorList = productDeviceMapper.selectSensorByGroupId(systemUser.getGroupId());
        AtomicReference<ProductDeviceDO> currentSensor = new AtomicReference<>();
        sensorList.forEach(sensorDO -> {
            if (sensorDO.getUniqueCode().equals(tableQuery.getUniqueCode())){
                isAllow.set(true);
                currentSensor.set(sensorDO);
            }
        });
        if(isAllow.get()){
            String checkKey = getRedisCheckKey(tableQuery);
            ProductDynamicTableCO result = redisService.getCacheObject(checkKey);
            if (null == result || !tableQuery.getSelectDate().before(DateUtils.getStartTime(new Date(),0)))
            {
                ProductDynamicTableCO finalResult = new ProductDynamicTableCO();
                finalResult.setDataItems(new ArrayList<>());
                finalResult.setColumns(new ArrayList<>());
                int modelId = currentSensor.get().getModelId();
                List<ProductDataItemDO> itemData = itemsMapper.selectItemsByModelId(modelId);
                List<String> dataIds = new ArrayList<>();
                itemData.forEach(item ->{
                    finalResult.getDataItems().add(toClientObject(item));
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
        return SingleResponse.of(new ProductDynamicTableCO());
    }

    private  String getRedisCheckKey(ProductDataTableQuery query){
        String prefix = "SENSOR_DYNAMIC_";
        return prefix + query.getUniqueCode()+"_"+query.getSelectDate();
    }
}
