package www.bwsensing.com.common.api;

import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;
import www.bwsensing.com.device.dto.clientobject.SensorDynamicColumnCO;
import java.util.Date;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ITimeSeriesDataService {
    /**
     * 获取最新数据
     * @param uniqueCode
     * @param dataId
     * @return
     */
    TimeSeriesDataCO getLastStatisticsData(String  uniqueCode,String dataId);

    /**
     * 获取传感器动态数据行
     * @param dataIds
     * @param selectDate
     * @param uniqueCode
     * @return
     */
    List<SensorDynamicColumnCO> getSensorDynamicColumns(List<String> dataIds, Date selectDate,String uniqueCode);
}
