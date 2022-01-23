package www.bwsensing.com.common.auth.service;

import cn.hutool.core.util.ArrayUtil;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;
import com.alibaba.cola.exception.BizException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import top.dcenter.ums.security.core.oauth.enums.ErrorCodeEnum;
import top.dcenter.ums.security.core.oauth.exception.RegisterUserFailureException;
import top.dcenter.ums.security.core.oauth.exception.UserNotExistException;
import top.dcenter.ums.security.core.oauth.service.UmsUserDetailsService;
import www.bwsensing.com.common.constant.SecurityConstants;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.AuthenticationGateway;
import www.bwsensing.com.domain.system.token.UserInfo;
import www.bwsensing.com.domain.system.user.SystemUser;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;
import javax.annotation.Resource;
import java.util.*;

/**
 *  用户密码与手机短信登录与注册服务：<br><br>
 *  1. 用于第三方登录与手机短信登录逻辑。<br><br>
 *  2. 用于用户密码登录逻辑。<br><br>
 *  3. 用户注册逻辑。<br><br>
 * @author YongWu zheng
 * @version V1.0  Created by 2020/9/20 11:06
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UmsUserDetailsService {

    @Autowired
    private UserCache userCache;

    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private AuthenticationGateway authenticationGateway;
    /**
     * 用于密码加解密
     */
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @SuppressWarnings("AlibabaUndefineMagicConstant")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try
        {
            // 从缓存中查询用户信息
            UserDetails cacheDetails = getUserDetailFromCache(username);
            if (null != cacheDetails)
            {
                log.info("北微云平台 ======>: 登录用户名：{}, 加载成功(cache)", username);
                return cacheDetails;
            }
            // 根据用户名获取用户信息
            UserInfo userInfo = authenticationGateway.getUserByName(username);
            // 获取用户信息逻辑。。。
            if (null != userInfo){
                // 示例：只是从用户登录日志表中提取的信息，
                log.info("北微云平台 ======>: 登录用户名：{}, 加载成功", username);
                UserDetails loadDetails = getUserDetails(userInfo);
                userCache.putUserInCache(loadDetails);
                return loadDetails;
            } else{
                throw new BizException("该用户不存在");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            String msg = String.format("北微云平台======>: 登录用户名：%s, 登录失败: %s", username, e.getMessage());
            log.error(msg);
            throw new UserNotExistException(ErrorCodeEnum.QUERY_USER_INFO_ERROR, e, username);
        }
    }

    private User getUserDetails(UserInfo info){
        if (info == null || info.getSystemUser() == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Set<String> dbAuthsSet = new HashSet<>();
        if (ArrayUtil.isNotEmpty(info.getUserRoles())) {
            // 获取角色
            Arrays.stream(info.getUserRoles()).forEach(role -> dbAuthsSet.add(SecurityConstants.ROLE + role));
            // 获取资源
            dbAuthsSet.addAll(Arrays.asList(info.getPermissions()));
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                .createAuthorityList(dbAuthsSet.toArray(new String[0]));
        SystemUser user = info.getSystemUser();
        // 构造security用户

        return new SmartUser(
                user.getId(),
                user.getGroupId(),
                user.getAccountName(),
                user.getPassword(),
                user.getMobile(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                true,
                user.getAccountNonLocked(),
                authorities);
    }

    @Override
    public UserDetails registerUser(AuthUser authUser, @NonNull String username, @NonNull String defaultAuthority, String decodeState)
            throws RegisterUserFailureException {
        // 第三方授权登录不需要密码, 这里随便设置的, 生成环境按自己的逻辑
        String encodedPassword = passwordEncoder.encode(authUser.getUuid());
        // 比如: 第三方登录成功后的跳转地址
        final RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // 假设 decodeState 就是 redirectUrl, 我们直接把 redirectUrl 设置到 request 上
        // 后续经过成功处理器时直接从 requestAttributes.getAttribute("redirectUrl", RequestAttributes.SCOPE_REQUEST) 获取并跳转
        assert requestAttributes != null;
        requestAttributes.setAttribute("redirectUrl", decodeState, RequestAttributes.SCOPE_REQUEST);
        // 当然 decodeState 也可以传递从前端传到后端的用户信息, 注册到本地用户
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(defaultAuthority);
        // ... 用户注册逻辑
        log.info("北微云平台 ======>: 用户名：{}, 注册成功", username);
        // @formatter:off
        UserDetails user = User.builder()
                .username(username)
                .password(encodedPassword)
                .disabled(false)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .authorities(grantedAuthorities)
                .build();
        // @formatter:off
        // 把用户信息存入缓存
        if (userCache != null)
        {
            userCache.putUserInCache(user);
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        UserDetails userDetails = loadUserByUsername(userId);
        User.withUserDetails(userDetails);
        return User.withUserDetails(userDetails).build();
    }

    /**
     * {@link #existedByUsernames(String...)} usernames 生成规则.
     * @param authUser     第三方用户信息
     * @return  返回一个 username 数组
     */
    @Override
    public String[] generateUsernames(AuthUser authUser) {
        return new String[]{
                authUser.getUsername(),
                // providerId = authUser.getSource()
                authUser.getUsername() + "_" + authUser.getSource(),
                // providerUserId = authUser.getUuid()
                authUser.getUsername() + "_" + authUser.getSource() + "_" + authUser.getUuid()
        };
    }

    @Override
    public List<Boolean> existedByUsernames(String... usernames) throws UsernameNotFoundException {
        //在本地账户上查询 userIds 是否已被使用
        List<Boolean> list = new ArrayList<>();
        for (String accountName : usernames){
            SystemUserDO sqlUser = systemUserMapper.selectUserByAccountName(accountName);
            if (StringUtils.isNotEmpty(sqlUser.getAccountName())){
                list.add(true);
            } else{
                list.add(false);
            }
        }
        return list;
    }

    private UserDetails getUserDetailFromCache(String username){
        if (this.userCache != null)
        {
            return this.userCache.getUserFromCache(username);
        }
        return null;
    }

}
