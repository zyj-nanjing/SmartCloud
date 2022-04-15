package www.bwsensing.com.device.api;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.device.dto.clientobject.SensorDynamicTableCO;
import www.bwsensing.com.device.dto.command.query.SensorDataSortQuery;
import www.bwsensing.com.device.dto.clientobject.SensorDataCO;
import www.bwsensing.com.device.dto.command.query.SensorTableQuery;

/**
 * 设备数据服务
 * @author macos-zyj
 */
public interface SensorDataService {
    /**
     * 设备监控数据组合查询
     * @param dataSortQuery
     * @return
     */
    @Deprecated
    PageResponse<SensorDataCO> sensorDataQuerySort(SensorDataSortQuery dataSortQuery);

    /**
     * 动态表格查询
     * @param tableQuery
     * @return
     */
    SingleResponse<SensorDynamicTableCO> sensorDynamicTableQuery(SensorTableQuery tableQuery);
}
