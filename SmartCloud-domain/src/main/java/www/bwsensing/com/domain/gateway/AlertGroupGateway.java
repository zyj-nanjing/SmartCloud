package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.device.alert.AlertGroup;

/**
 * @author macos-zyj
 */
public interface AlertGroupGateway {
    /**
     * 保存
     * @param saveGroup
     */
    void saveGroup(AlertGroup saveGroup);

    /**
     * 修改分组
     * @param updateGroup
     */
    void updateGroup(AlertGroup updateGroup);

    /**
     * 删除
     * @param id
     */
    void deleteGroup(Integer id);
}
