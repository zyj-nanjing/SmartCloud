package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.token.TokenData;

/**
 * @author macos-zyj
 */
public interface TokenGateway {
    /**
     * 获取Token信息
     * @return
     */
    TokenData getTokenInfo();
}
