package www.bwsensing.com.project.system.gateway;


import com.alibaba.cola.exception.BizException;
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

    @Override
    public TokenData getTokenInfo() {
        TokenData tokenData = new TokenData();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if ( null == securityContext ){
            throw new BizException("TOKEN_IS_NULL","token不能为空");
        }
        Authentication  authority = securityContext.getAuthentication();
        initUserAuthInfo(tokenData,authority);
        setUserRoleAuth(tokenData,authority);
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
    }
}
