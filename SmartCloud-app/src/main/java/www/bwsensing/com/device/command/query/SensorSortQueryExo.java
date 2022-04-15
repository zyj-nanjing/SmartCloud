package www.bwsensing.com.device.command.query;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.convertor.SensorCoConvertor;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.device.dto.command.query.SensorSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorCO;
import www.bwsensing.com.device.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.SensorDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SensorSortQueryExo {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SensorMapper sensorMapper;

    public PageResponse<SensorCO> execute(SensorSortQuery sortQuery){
        PageHelperUtils<SensorSortQuery, SensorDO> pageHelper =
                PageHelperUtils.<SensorSortQuery,SensorDO>builder()
                        .pageFunction((query)->sensorMapper.selectSensorBySort (initSensorQuery(query))).build();
        PageInfo<SensorDO> page= pageHelper.getPageCollections(sortQuery);
        List<SensorCO> result = SensorCoConvertor.toClientObjectArray(page.getList());
        return PageResponse.of(result,(int)page.getTotal(),page.getPageSize(),sortQuery.getPageIndex());
    }

    public MultiResponse<SensorCO> executeTotal(SensorSortQuery sortQuery){
        List<SensorDO> sensorCollection= sensorMapper.selectSensorBySort (initSensorQuery(sortQuery));
        List<SensorCO> result = SensorCoConvertor.toClientObjectArray(sensorCollection);
        return MultiResponse.of(result);
    }

    private SensorDO initSensorQuery(SensorSortQuery sortQuery){
        SensorDO query = new SensorDO();
        query.setMemberGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setName(sortQuery.getName());
        query.setProjectId(sortQuery.getProjectId());
        query.setManagerId(sortQuery.getManagerId());
        query.setModelId(sortQuery.getModelId());
        query.setIsOrderByOnlineTime(sortQuery.isOrderByOnlineTime());
        query.setIsOrderByUpLineTime(sortQuery.isOrderByUpLineTime());
        return query;
    }
}
