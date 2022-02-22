package www.bwsensing.com.project.system.service.impl;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.GrantedAuthority;
import www.bwsensing.com.common.cache.redis.RedisService;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import www.bwsensing.com.system.dto.clientobject.PermissionCO;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.PermissionService;
import javax.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import java.util.*;

/**
 * @author macos-zyj
 */
@Component
public class SmartAuthorityService {
    private static final String ROLE_MAP_KEY ="PERMISSION_MAP";

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisService redisService;

    /**
     * 判断是否有权限
     *
     * @param request
     * @param authentication
     * @return
     */
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Collection<ConfigAttribute> collection = getAttributes(request);
        if (null == collection || collection.size() <= 0) {
            return true;
        }
        ConfigAttribute configAttribute;
        String needRole;
        for (ConfigAttribute attribute : collection) {
            configAttribute = attribute;
            needRole = configAttribute.getAttribute();
            for (GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
                if (needRole.trim().equals(grantedAuthority.getAuthority())) {
                    return true;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

    /**
     * 判定用户请求的url是否在权限表中，如果在权限表中，则返回decide方法，
     * 用来判定用户是否有权限，如果不在权限表中则放行
     *
     * @param request
     * @return
     * @throws IllegalArgumentException
     */
    public Collection<ConfigAttribute> getAttributes(HttpServletRequest request) throws IllegalArgumentException {
        Map<String, Collection<ConfigAttribute>> map = getPermissionFromRedis();
        for (Map.Entry<String, Collection<ConfigAttribute>> entry : map.entrySet()) {
            String url = entry.getKey();
            if (new AntPathRequestMatcher(url).matches(request)) {
                return map.get(url);
            }
        }
        return null;
    }

    /**
     * 获取分配的权限
     * @param username
     * @return
     */
    public List<GrantedAuthority> getGrantedAuthority(String username){
        return new ArrayList<>();
    }

    /**
     * 加载权限表中所有权限
     */
    private Map<String, Collection<ConfigAttribute>> loadResourceDefine() {
        Map<String, Collection<ConfigAttribute>> roleMap = new HashMap<>(16);
        List<PermissionCO> all = permissionService.selectAllPermission();
        for (PermissionCO permission : all) {
            List<ConfigAttribute> configAttributeList =
                    permission.getRoleNames().stream().map(roleName ->
                            (ConfigAttribute) new SecurityConfig("ROLE_" + roleName.toUpperCase()))
                            .collect(Collectors.toList());
            roleMap.put(permission.getPermissionUrl(), configAttributeList);
        }
        putPermissionInRedis(roleMap);
        return roleMap;
    }

    private Map<String, Collection<ConfigAttribute>> getPermissionFromRedis(){
        return  redisService.getCacheMap(ROLE_MAP_KEY);
    }

    private void putPermissionInRedis(Map<String, Collection<ConfigAttribute>> permissionMap){
        redisService.setCacheMap(ROLE_MAP_KEY,permissionMap);
    }
}
