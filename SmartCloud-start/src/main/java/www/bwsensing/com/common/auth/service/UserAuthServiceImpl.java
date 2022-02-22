package www.bwsensing.com.common.auth.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.UserAuthService;
import www.bwsensing.com.common.utills.Md5Utils;
import www.bwsensing.com.common.utils.ServletUtils;
import www.bwsensing.com.common.constant.TokenConstant;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.utills.TokenUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.system.dto.command.UserLoginCmd;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author macos-zyj
 */
@Slf4j
@CatchAndLog
@Component
public class UserAuthServiceImpl implements UserAuthService {
    private static final String USER_TOKEN_KEY_FORMAT = "TOKEN_KEY_";
    @Resource
    private SystemUserGateway systemUserGateway;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private RedisService redisService;


    @Override
    public SingleResponse<String> login(UserLoginCmd loginCmd) {
        //用户验证
        final Authentication authentication = authenticate(loginCmd.getAccountName(), loginCmd.getPassword());
        //存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成token
        final UserDetails user = (UserDetails) authentication.getPrincipal();
        String userRole = systemUserGateway.getUserRole(loginCmd.getAccountName());
        Assert.isTrue(StringUtils.isNotEmpty(userRole),"用户角色不能为空");
        final String token = TokenUtils.createToken(user.getUsername(),userRole,true);
        //存储token
        tokenSave(user.getUsername(), token);
        return SingleResponse.of(token);
    }

    @Override
    public Response loginOut() {
        String tokenHeader = ServletUtils.getHead(TokenConstant.TOKEN_HEADER);
        String handlerToken = tokenHeader.replace(TokenConstant.TOKEN_PREFIX,"");
        String tokenKeyword = Md5Utils.encryptMd5(handlerToken);
        deleteRedisToken(tokenKeyword);
        return Response.buildSuccess();
    }

    @Override
    public String getTokenCheckKey(String accountName) {
        return Md5Utils.encryptMd5(USER_TOKEN_KEY_FORMAT + accountName);
    }

    private void tokenSave(String userName,String token){
        String keyword = getTokenCheckKey(userName);
        String tokenKeyword = Md5Utils.encryptMd5(token);
        deleteRedisToken(keyword);
        redisService.setCacheObject(keyword,tokenKeyword, TokenConstant.TOKEN_EXIST_TIME, TimeUnit.HOURS);
        redisService.setCacheObject(tokenKeyword,keyword,TokenConstant.TOKEN_EXIST_TIME, TimeUnit.HOURS);
    }

    private void  deleteRedisToken(String checkToken){
        if(redisService.hasKey(checkToken)){
            String result = redisService.getCacheObject(checkToken);
            if (redisService.hasKey(result)){
                redisService.deleteObject(result);
            }
            redisService.deleteObject(checkToken);
        }
    }

    private Authentication authenticate(String username, String password) {
        try {
            //该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确，则存储该用户名密码到“security 的 context中”
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException | BadCredentialsException e) {
            throw new BizException("LOGIN_ERROR",e.getMessage());
        }
    }


}
