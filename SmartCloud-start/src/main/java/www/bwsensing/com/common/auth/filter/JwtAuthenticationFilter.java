package www.bwsensing.com.common.auth.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import www.bwsensing.com.common.constant.TokenConstant;
import www.bwsensing.com.common.redis.RedisService;
import www.bwsensing.com.common.utills.Md5Utils;
import www.bwsensing.com.common.utills.TokenUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bxsheng
 * @blogAddress www.kdream.cn
 * @createTIme 2020/9/16
 * since JDK 1.8
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private RedisService redisService;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager,RedisService redisService) {
        super(authenticationManager);
        this.redisService = redisService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String tokenHeader = request.getHeader(TokenConstant.TOKEN_HEADER);
        //如果请求头中没有Authorization信息则直接放行了
        if(null== tokenHeader || !tokenHeader.startsWith(TokenConstant.TOKEN_PREFIX)){
            chain.doFilter(request, response);
            return;
        }
        String handlerToken = tokenHeader.replace(TokenConstant.TOKEN_PREFIX,"").replace(" ","");
        //如果请求头中有token,则进行解析，并且设置认证信息
        if(!TokenUtils.isExpiration(handlerToken)&&checkAuthentication(handlerToken)){
            //设置上下文
            UsernamePasswordAuthenticationToken authentication = getAuthentication(tokenHeader);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else{
            log.debug("Token error;Error Token:{}",handlerToken);
        }
        super.doFilterInternal(request, response, chain);
    }

    /**
     * 获取用户信息
     * @param tokenHeader
     * @return
     */
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader){
        String token = tokenHeader.replace(TokenConstant.TOKEN_PREFIX, "");
        String username = TokenUtils.getUserName(token);
        // 获得权限 添加到权限上去
        String role = TokenUtils.getUserRole(token);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add((GrantedAuthority) () -> role);
        if(username != null){
            return new UsernamePasswordAuthenticationToken(username, null,roles);
        }
        return null;
    }

    private boolean checkAuthentication(String token){
        return redisService.hasKey(tokenEncrypt(token));
    }

    private String tokenEncrypt(String token){
        return Md5Utils.encryptMd5(token);
    }
}

