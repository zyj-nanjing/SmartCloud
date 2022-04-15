package www.bwsensing.com.monitor.api;

import com.alibaba.cola.dto.MultiResponse;
import www.bwsensing.com.monitor.dto.clientobject.MonitorItemsCO;
import www.bwsensing.com.monitor.dto.command.query.ItemsQuery;

/**
 * @author macos-zyj
 */
public interface ItemsService {

    /**
     * 检测项组合查询
     * @param query
     * @return
     */
    MultiResponse<MonitorItemsCO> selectMonitorItemsBySort(ItemsQuery query);

    /**
     * 按照sn查询 数据可视化替换使用
     * @param sn
     * @return
     */
    MultiResponse<MonitorItemsCO>  selectMonitorItemsBySn(String sn);

    /**
     * 按照Id查询 数据可视化替换使用
     * @param sensorId
     * @return
     */
    MultiResponse<MonitorItemsCO>  selectMonitorItemsBySensorId(Integer sensorId);
}
