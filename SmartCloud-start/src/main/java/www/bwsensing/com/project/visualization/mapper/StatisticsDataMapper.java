package www.bwsensing.com.project.visualization.mapper;

import www.bwsensing.com.project.visualization.domain.StatisticsData;
import www.bwsensing.com.project.visualization.domain.MonitorQuery;
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
    List<StatisticsData> listStatisticsAvgData(MonitorQuery query);


    /**
     * 极值查询
     * @param query
     * @return
     */
    List<StatisticsData> listStatisticsSpreadData(MonitorQuery query);


    /**
     * 获取当前最新数据
     * @param uniqueCode
     * @param dataId
     * @return
     */
    StatisticsData getLastStatisticsData(String  uniqueCode,String dataId);
}
