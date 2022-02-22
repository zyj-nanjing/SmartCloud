package www.bwsensing.com.domain.system.gateway;

import www.bwsensing.com.domain.system.model.token.TokenData;
/**
 * @author macos-zyj
 */
@Deprecated
public interface TokenGateway {
    /**
     * 获取Token信息
     * @return
     */
    TokenData getTokenInfo();
}
