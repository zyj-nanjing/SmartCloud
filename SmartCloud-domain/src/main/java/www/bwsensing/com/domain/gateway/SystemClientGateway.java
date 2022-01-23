package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.client.SystemClient;

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


}
