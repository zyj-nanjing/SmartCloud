package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.monitor.MonitorPrototype;

import java.util.List;

/**
 * 监测类型领域网关
 * @author macos-zyj
 */
public interface PrototypeGateway {
    /**
     * 创建
     * @param monitorPrototype
     */
    void createPrototype(MonitorPrototype monitorPrototype);

    /**
     * 修改
     * @param monitorPrototype
     */
    void updatePrototype(MonitorPrototype monitorPrototype);

    /**
     * 删除 修改状态
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id结合获取监测类型
     * @param ids
     * @return
     */
    List<MonitorPrototype> getMonitorPrototypesByIds(List<Integer> ids);
}
