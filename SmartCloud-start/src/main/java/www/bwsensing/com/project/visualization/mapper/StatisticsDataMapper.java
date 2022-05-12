package www.bwsensing.com.project.visualization.mapper;

import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.domain.DeviceDataQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsQuery;
import java.util.List;

/**
 * 后续需要移到领域网关里面
 * 时序可视化数据库查询
 * @author macos-zyj
 */
public interface StatisticsDataMapper {
    /**
     * 平均查询
     * @param query
     * @return
     */
    List<StatisticsData> listStatisticsAvgData(DeviceDataQuery query);


    /**
     * 极值查询
     * @param query
     * @return
     */
    List<StatisticsData> listStatisticsSpreadData(DeviceDataQuery query);

    /**
     * 最大值查询
     * @param query
     * @return
     */
    List<StatisticsData> listStatisticsMaxData(DeviceDataQuery query);

    /**
     * 最小值查询
     * @param query
     * @return
     */
    List<StatisticsData> listStatisticsMinData(DeviceDataQuery query);
    /**
     * 数据查询
     * @param statisticsQuery
     * @return
     */
    List<StatisticsData> listStatisticsCollection(StatisticsQuery statisticsQuery);


    /**
     * 获取当前最新数据
     * @param uniqueCode
     * @param dataId
     * @return
     */
    StatisticsData getLastStatisticsData(String  uniqueCode,String dataId);
}
