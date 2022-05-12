package www.bwsensing.com.domain.device.gateway;

import www.bwsensing.com.domain.device.model.data.MonitorData;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface DeviceSeriesDataGateway {
    /**
     * 获取时间区间内设备的平均数据
     * @param uniqueCode
     * @param dataId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MonitorData> getDeviceSeriesDataWithAvg(String uniqueCode, String dataId, Timestamp startTime,Timestamp endTime);

    /**
     * 获取时间区间内设备的极值数据
     * @param uniqueCode
     * @param dataId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MonitorData> getDeviceSeriesDataWithSpread(String uniqueCode, String dataId, Timestamp startTime,Timestamp endTime);

    /**
     * 获取时间区间内设备的最大数据
     * @param uniqueCode
     * @param dataId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MonitorData> getDeviceSeriesDataWithMax(String uniqueCode, String dataId, Timestamp startTime,Timestamp endTime);

    /**
     * 获取时间区间内设备的最小数据
     * @param uniqueCode
     * @param dataId
     * @param startTime
     * @param endTime
     * @return
     */
    List<MonitorData> getDeviceSeriesDataWithMin(String uniqueCode, String dataId, Timestamp startTime,Timestamp endTime);
}
