package www.bwsensing.com.command.query;

import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.SensorCoConvertor;
import www.bwsensing.com.dto.clientobject.SensorCO;
import www.bwsensing.com.dto.command.query.SensorSortQuery;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SensorSortQueryExo {

    @Resource
    private SensorMapper sensorMapper;

    public PageResponse<SensorCO> execute(SensorSortQuery sortQuery){
        PageHelper.startPage(sortQuery.getPageIndex(), sortQuery.getPageSize());
        List<SensorDO> sensorList =sensorMapper.selectSensorBySort(initSensorQuery(sortQuery));
        PageInfo pageInfo = new PageInfo<>(sensorList);
        List<SensorCO> result = SensorCoConvertor.toClientObjectArray(sensorList);
        return PageResponse.of(result,(int)pageInfo.getTotal(),pageInfo.getPageSize(),sortQuery.getPageIndex());
    }


    private SensorDO initSensorQuery(SensorSortQuery sortQuery){
        SensorDO query = new SensorDO();
        query.setName(sortQuery.getName());
        query.setProjectId(sortQuery.getProjectId());
        query.setManagerId(sortQuery.getManagerId());
        query.setModelId(sortQuery.getModelId());
        query.setIsOrderByOnlineTime(sortQuery.isOrderByOnlineTime());
        query.setIsOrderByUpLineTime(sortQuery.isOrderByUpLineTime());
        return query;
    }
}
