package www.bwsensing.com.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.ItemsService;
import www.bwsensing.com.convertor.ItemsCoConvertor;
import www.bwsensing.com.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.dto.command.query.ItemsQuery;
import www.bwsensing.com.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class IItemsServiceImpl implements ItemsService {
    @Resource
    private MonitorItemsMapper  itemsMapper;
    @Resource
    private SensorMapper sensorMapper;

    @Override
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySort(ItemsQuery query) {
        List<MonitorItemsDO> itemData;
        if(null != query.getModelId()){
            itemData = itemsMapper.selectItemsByModelId(query.getModelId());
        }else if (null != query.getTypeIdId()){
            itemData = itemsMapper.selectItemsByTypeId(query.getTypeIdId());
        } else {
            itemData = itemsMapper.selectItems();
        }
        return MultiResponse.of(ItemsCoConvertor.toClientObjectList(itemData));
    }

    @Override
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySn(String sn) {
        SensorDO sensorDo = sensorMapper.selectSensorBySn(sn);
        if(null != sensorDo){
            List<MonitorItemsDO> itemData = itemsMapper.selectItemsByModelId(sensorDo.getModelId());
            return MultiResponse.of(ItemsCoConvertor.toClientObjectList(itemData));
        }
        throw new BizException("SENSOR_SN_NOT_FOUND","传感器SN编号不存在");
    }
}
