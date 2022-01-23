package www.bwsensing.com.gatewayimpl.filter;

import javax.annotation.Resource;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.core.filter.Filter;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.system.role.UserRole;
import www.bwsensing.com.common.core.filter.FilterInvoker;
import www.bwsensing.com.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;


/**
 * @author macos-zyj
 */
@Component
public class SystemRoleNameRepeatFilter implements Filter<UserRole> {
    @Resource
    private SystemRoleMapper roleMapper;

    @Override
    public void doFilter(UserRole context, FilterInvoker nextFilter) {
        if (null == context.getId()){
            validateFilter(context.getRoleCode());
        } else {
            SystemRoleDO systemRole = roleMapper.getUserRoleById(context.getId());
            if (StringUtils.isNotEmpty(context.getRoleName())){
                if (!systemRole.getRoleName().equals(context.getRoleName())){
                    validateFilter(context.getRoleCode());
                }
            }
        }
        nextFilter.invoke(context);
    }

    private void validateFilter(String roleName){
        if (roleMapper.countUserRoleByName(roleName) > 0 ){
            throw new BizException("ROLE_ADD_OR_EDIT_ERROR","当前角色名称已存在");
        }
    }
}
