package www.bwsensing.com.monitor.service;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.monitor.api.ItemsService;
import www.bwsensing.com.monitor.convertor.ItemsCoConvertor;
import www.bwsensing.com.monitor.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.monitor.dto.command.query.ItemsQuery;
import www.bwsensing.com.device.gatewayimpl.database.MonitorItemsMapper;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorItemsDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
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

    @Override
    public MultiResponse<MonitorItemsCO> selectMonitorItemsBySensorId(Integer sensorId) {
        SensorDO sensorDo = sensorMapper.selectSensorById(sensorId);
        if(null != sensorDo){
            List<MonitorItemsDO> itemData = itemsMapper.selectItemsByModelId(sensorDo.getModelId());
            return MultiResponse.of(ItemsCoConvertor.toClientObjectList(itemData));
        }
        throw new BizException("SENSOR_ID_NOT_FOUND","传感器编号不存在");
    }
}
