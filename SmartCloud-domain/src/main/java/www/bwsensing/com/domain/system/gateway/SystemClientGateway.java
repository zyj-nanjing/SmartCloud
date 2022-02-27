package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.client.SystemClient;

/**
 * 客户领域网关
 * @author macos-zyj
 */
public interface SystemClientGateway {
    /**
     * 保存客户信息
     * @param systemClient
     */
    void saveSystemClient(SystemClient systemClient);

    /**
     * 修改客户信息
     * @param systemClient
     * @return
     */
    void updateSystemClient(SystemClient systemClient);

    /**
     * 删除客户信息
     * @param clientId
     * @return
     */
    void deleteSystemClient(Integer clientId);

    /**
     * 根据编号获取客户信息
     * @param id
     * @return
     */
    SystemClient getSystemClientById(Integer id);
}
