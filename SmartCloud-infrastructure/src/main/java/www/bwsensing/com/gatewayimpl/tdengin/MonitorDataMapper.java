package www.bwsensing.com.gatewayimpl.tdengin;

import www.bwsensing.com.domain.device.data.MonitorData;
import java.util.List;

/**
 * TDengine 传感器数据 关联
 * @author macos-zyj
 */
public interface MonitorDataMapper {
    /**
     * 创建对应的超级表
     * @return int
     */
    int createSuperTable();

    /**
     * 创建对应的新子表
     * @param monitorData
     * @return int
     */
    int createNewDataTable(MonitorData monitorData);

    /**
     * 插入数据
     * @param monitorData
     * @return int
     */
    int insertMonitorData(MonitorData monitorData);

    /**
     * 获取解析数据总量
     * @param sn
     * @return
     */
    Integer queryMonitorDataSize(String sn);

    /**
     * 批量插入数据
     * @param dataCollection
     * @return
     */
    int[]  batchMonitorData(List<MonitorData> dataCollection);
}
