package www.bwsensing.com.device.service;

import com.alibaba.cola.dto.PageResponse;
import org.springframework.stereotype.Service;
import www.bwsensing.com.device.api.SensorDataService;
import www.bwsensing.com.device.command.query.SensorDataSortQueryExo;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Service
public class ISensorDataServiceImpl implements SensorDataService {

    @Resource
    private SensorDataSortQueryExo sensorDataSortQueryExo;

    @Override
    public PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery) {
        return sensorDataSortQueryExo.execute(dataSortQuery);
    }
}
