package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.monitor.MonitorStructure;
import www.bwsensing.com.domain.monitor.model.MonitorStructureModel;

import java.util.List;

/**
 *  结构体领域网关
 * @author macos-zyj
 */
public interface StructureModelGateway {
    /***
     * 查询
     * @return
     */
    List<MonitorStructure> selectMonitorStructureBySort();
    /**
     *  保存
     * @param monitorStructure
     */
    void saveModel(MonitorStructureModel monitorStructure);

    /**
     *  修改
     * @param monitorStructure
     */
    void updateModel(MonitorStructureModel monitorStructure);

    /**
     * 根据结构物Code删除
     * @param code
     */
    void deleteByCode(String code);
    /**
     * 获取最新的结构物模板
     * @param structureCode
     * @return
     */
    MonitorStructureModel getMonitorStructureModelByCode(String structureCode);
}
