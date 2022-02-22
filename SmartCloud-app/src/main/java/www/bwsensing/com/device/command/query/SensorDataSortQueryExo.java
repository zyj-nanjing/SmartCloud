package www.bwsensing.com.device.command.query;

import java.util.List;
import javax.annotation.Resource;
import com.github.pagehelper.PageInfo;
import com.alibaba.cola.dto.PageResponse;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;
import www.bwsensing.com.device.convertor.SensorDataCoConvertor;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.gatewayimpl.database.SensorDataMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDataDO;


/**
 * @author macos-zyj
 */
@Component
public class SensorDataSortQueryExo {
    @Resource
    private SensorDataMapper sensorDataMapper;

    public PageResponse<SensorDataCO> execute(SensorDataSortQuery dataSortQuery){
        PageHelperUtils<SensorDataSortQuery, SensorDataDO> pageHelper =
                PageHelperUtils.<SensorDataSortQuery,SensorDataDO>builder()
                        .pageFunction((groupQuery)->sensorDataMapper.querySensorListBySort(initializeQuery(dataSortQuery))).build();
        PageInfo<SensorDataDO> pageInfo= pageHelper.getPageCollections(dataSortQuery);
        List<SensorDataCO> result = SensorDataCoConvertor.toClientObjectArray(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),dataSortQuery.getPageIndex() );
    }

    private  SensorDataDO initializeQuery(SensorDataSortQuery query){
        SensorDataDO queryData = new SensorDataDO();
        queryData.setSn(query.getSnCode());
        queryData.setStructureId(query.getStructureId());
        queryData.setStartTime(query.getStartTime());
        queryData.setEndTime(query.getEndTime());
        return queryData;
    }
}
