package www.bwsensing.com.command.query;

import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.SensorDataCoConvertor;
import www.bwsensing.com.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.dto.clientobject.SensorDataCO;
import www.bwsensing.com.gatewayimpl.database.SensorDataMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDataDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SensorDataSortQueryExo {
    @Resource
    private SensorDataMapper sensorDataMapper;

    public PageResponse<SensorDataCO> execute(SensorDataSortQuery dataSortQuery){
        PageHelper.startPage(dataSortQuery.getPageIndex(), dataSortQuery.getPageSize());
        List<SensorDataDO> sensorDataList = sensorDataMapper.querySensorListBySort(initQuerySensorData(dataSortQuery));
        PageInfo pageInfo = new PageInfo<>(sensorDataList);
        List<SensorDataCO> result = SensorDataCoConvertor.toClientObjectArray(sensorDataList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),dataSortQuery.getPageIndex() );
    }

    private  SensorDataDO initQuerySensorData(SensorDataSortQuery query){
        SensorDataDO queryData = new SensorDataDO();
        queryData.setSn(query.getSnCode());
        queryData.setStructureId(query.getStructureId());
        queryData.setStartTime(query.getStartTime());
        queryData.setEndTime(query.getEndTime());
        return queryData;
    }
}
