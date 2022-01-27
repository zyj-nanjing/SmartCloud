package www.bwsensing.com.project.system.gateway;


import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.common.cache.ehcache.EhCacheService;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class TokenGatewayImpl implements TokenGateway {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private EhCacheService ehCacheService;

    @Override
    public TokenData getTokenInfo() {
        TokenData tokenData = new TokenData();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication  authority = securityContext.getAuthentication();
        String currentAccountName =(String) authority.getPrincipal();
        if (null == ehCacheService.getTemp(currentAccountName)){
            if ( null == securityContext ){
                throw new BizException("TOKEN_IS_NULL","token不能为空");
            }
            initUserAuthInfo(tokenData,authority);
            setUserRoleAuth(tokenData,authority);
            ehCacheService.putTemp(currentAccountName,tokenData);
        } else {
            Object cacheObj = ehCacheService.getTemp(currentAccountName);
            if (StringUtils.isNotNull(cacheObj))
            {
                return StringUtils.cast(cacheObj);
            }
        }
        return tokenData;
    }

    private void setUserRoleAuth(TokenData tokenData, Authentication  authority){
        List<GrantedAuthority> roleList = (List<GrantedAuthority>) authority.getAuthorities();
        tokenData.setRole(roleList.get(0).getAuthority());
    }

    private void initUserAuthInfo(TokenData tokenData, Authentication  authority){
        String accountName = (String) authority.getPrincipal();
        tokenData.setAccountName(accountName);
        SystemUserDO userData = systemUserMapper.selectUserByAccountName(accountName);
        tokenData.setUserId(userData.getId());
        tokenData.setUserName(userData.getUserName());
        tokenData.setGroupId(userData.getOperateGroupId());
        tokenData.setIsAdmin(userData.getIsAdmin());
    }
}
