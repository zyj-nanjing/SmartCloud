package www.bwsensing.com.domain.gateway;


import www.bwsensing.com.domain.monitor.MonitorPosition;
import www.bwsensing.com.domain.monitor.MonitorStructure;

/**
 * @author macos-zyj
 */
public interface StructureGateway {
    /**
     * 保存结构体
     * @param monitorStructure
     */
    void saveStructure(MonitorStructure monitorStructure);

    /**
     * 保存测点
     * @param monitorPosition
     */
    void savePosition(MonitorPosition monitorPosition);

    /**
     * 修改结构物
     * @param monitorStructure
     */
    void updateStructure(MonitorStructure monitorStructure);
    /**
     * 根据Id获取实例化结构物
     * @param structureId
     * @return
     */
    MonitorStructure getMonitorStructureById(Integer structureId);

}
