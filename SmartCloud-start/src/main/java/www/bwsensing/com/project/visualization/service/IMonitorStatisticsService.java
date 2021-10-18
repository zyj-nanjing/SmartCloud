package www.bwsensing.com.project.visualization.service;

import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.project.visualization.domain.MonitorQuery;
import www.bwsensing.com.project.visualization.domain.StatisticsResult;
import java.util.Map;

/**
 * @author macos-zyj
 */
public interface IMonitorStatisticsService {

    /**
     * 单设备数据解析
     * @param query
     * @return
     */
    SingleResponse<Map<String, StatisticsResult>> singleDataAnalyse(MonitorQuery query);
}
