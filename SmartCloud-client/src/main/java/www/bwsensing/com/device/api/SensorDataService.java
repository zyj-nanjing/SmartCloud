package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.PageResponse;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;

/**
 * 设备数据服务
 * @author macos-zyj
 */
@Deprecated
public interface SensorDataService {
    /**
     * 设备监控数据组合查询
     * @param dataSortQuery
     * @return
     */
    @Deprecated
    PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery);

}
