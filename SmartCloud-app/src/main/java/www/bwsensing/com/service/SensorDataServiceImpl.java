package www.bwsensing.com.service;

import com.alibaba.cola.dto.PageResponse;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.ISensorDataService;
import www.bwsensing.com.command.query.SensorDataSortQueryExo;
import www.bwsensing.com.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.dto.clientobject.SensorDataCO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Service
public class SensorDataServiceImpl implements ISensorDataService {

    @Resource
    private SensorDataSortQueryExo sensorDataSortQueryExo;

    @Override
    public PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery) {
        return sensorDataSortQueryExo.execute(dataSortQuery);
    }
}
