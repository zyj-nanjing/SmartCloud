package www.bwsensing.com.common.api;

import www.bwsensing.com.common.clientobject.TimeSeriesDataCO;

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
}
