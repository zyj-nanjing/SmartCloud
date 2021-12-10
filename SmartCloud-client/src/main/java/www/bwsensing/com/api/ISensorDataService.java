package www.bwsensing.com.api;

import com.alibaba.cola.dto.PageResponse;
import www.bwsensing.com.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.dto.clientobject.SensorDataCO;

/**
 * 设备数据服务
 * @author macos-zyj
 */
public interface ISensorDataService {
    /**
     * 设备监控数据组合查询
     * @param dataSortQuery
     * @return
     */
    PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery);

}
