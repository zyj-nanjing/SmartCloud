package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.token.UserInfo;
/**
 * 权限及用户信息相关能力
 * @author macos-zyj
 */
public interface AuthenticationGateway {
    /**
     * 获取当前用户
     * @return
     */
    UserInfo getCurrentUser();

    /**
     * 根据名称获取用户信息
     * @param username
     * @return
     */
    UserInfo getUserByName(String username);
}
