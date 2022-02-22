package www.bwsensing.com.project.system.gateway;

import java.util.Set;
import java.util.List;
import net.sf.ehcache.Cache;
import java.util.Collection;
import net.sf.ehcache.Element;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import cn.hutool.core.util.StrUtil;
import java.util.stream.Collectors;
import cn.hutool.core.util.ArrayUtil;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.PermissionDO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemUserDO;
import www.bwsensing.com.system.gatewayimpl.database.PermissionMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.domain.system.gateway.AuthenticationGateway;
import www.bwsensing.com.common.cache.ehcache.EhCacheService;
import www.bwsensing.com.domain.system.gateway.PermissionGateway;
import www.bwsensing.com.common.constant.EhCacheConstant;
import www.bwsensing.com.system.convertor.SystemUserConvertor;
import www.bwsensing.com.domain.system.model.token.UserInfo;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.model.role.Permission;
import www.bwsensing.com.domain.system.model.role.UserRole;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class AuthenticationGatewayImpl implements AuthenticationGateway {

    @Resource
    private SystemUserMapper systemUserMapper;

    @Resource
    private EhCacheService ehCacheService;

    @Resource
    private PermissionGateway permissionGateway;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public String getAccountName() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if ( null == securityContext ){
            throw new BizException("TOKEN_IS_NULL","token不能为空");
        }
        Authentication authority = securityContext.getAuthentication();
        return (String) authority.getPrincipal();
    }

    @Override
    public UserInfo getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        if ( null == securityContext ){
            throw new BizException("TOKEN_IS_NULL","token不能为空");
        }
        Authentication authority = securityContext.getAuthentication();
        String accountName = (String) authority.getPrincipal();
        return getUserByName(accountName);
    }

    @Override
    public UserInfo getUserByName(String username) {
        Cache cache = ehCacheService.getCache(EhCacheConstant.USER_DETAILS);
        if (cache != null && cache.get(username) != null) {
            return (UserInfo) cache.get(username).getObjectValue();
        }
        UserInfo dataInfo = new UserInfo();
        SystemUserDO userData = systemUserMapper.selectUserByAccountName(username);
        if (null != userData ){
            SystemUser domainUser = SystemUserConvertor.toDomain(userData);
            // 设置角色列表
            List<UserRole> roleList = permissionGateway.listRolesByUserId(domainUser.getId());
            domainUser.setRoles(roleList);
            // 设置角色列表 （ID）
            List<Integer> roleIds = roleList.stream().map(UserRole::getId).collect(Collectors.toList());
            dataInfo.setUserRoles(ArrayUtil.toArray(roleIds, Integer.class));
            Set<String> permissions = getPermissions(domainUser,roleIds);
            dataInfo.setPermissions(ArrayUtil.toArray(permissions, String.class));
            dataInfo.setSystemUser(domainUser);
            assert cache != null;
            cache.put(new Element(username,dataInfo));
        }
        return dataInfo;
    }

    private Set<String> getPermissions(SystemUser domainUser,List<Integer> roleIds){
        if (domainUser.getIsAdmin()){
            List<PermissionDO> dataCollectionList = permissionMapper.selectPermission();
            return dataCollectionList.stream().map(PermissionDO::getCode)
                    .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        } else {
            return roleIds.stream().map(permissionGateway::getPermissionsByRoleId).flatMap(Collection::stream)
                    .map(Permission::getCode)
                    .filter(StrUtil::isNotBlank).collect(Collectors.toSet());
        }
    }

}
