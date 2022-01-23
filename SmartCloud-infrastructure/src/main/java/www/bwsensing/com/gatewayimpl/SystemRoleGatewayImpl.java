package www.bwsensing.com.gatewayimpl;

import javax.annotation.Resource;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.convertor.SystemRoleConvertor;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.menu.SystemMenu;
import www.bwsensing.com.domain.system.role.UserRole;
import www.bwsensing.com.common.core.filter.FilterChain;
import www.bwsensing.com.domain.gateway.SystemRoleGateway;
import www.bwsensing.com.common.core.filter.FilterChainFactory;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;
import www.bwsensing.com.gatewayimpl.filter.SystemRoleCodeRepeatFilter;
import www.bwsensing.com.gatewayimpl.filter.SystemRoleNameRepeatFilter;

import java.util.Date;

/**
 * @author macos-zyj
 */
@Component
public class SystemRoleGatewayImpl implements SystemRoleGateway {

    private static FilterChain<UserRole> filterChain = FilterChainFactory.buildFilterChain(
            SystemRoleCodeRepeatFilter.class,
            SystemRoleNameRepeatFilter.class
    );

    @Resource
    private SystemRoleMapper roleMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void saveSystemRole(UserRole userRole) {
        filterChain.doFilter(userRole);
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemRoleDO dataObject = SystemRoleConvertor.toDataObject(userRole);
        dataObject.setCreator(tokenData.getAccountName());
        dataObject.setCreateTime(new Date());
        roleMapper.saveUserRole(dataObject);
        for (SystemMenu selectedMenu:userRole.getSystemMenus()) {
            roleMapper.insertMenuRole(dataObject.getId(), selectedMenu.getId());
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void updateSystemRole(UserRole userRole) {
        SystemRoleDO currentObject = roleMapper.getUserRoleById(userRole.getId());
        if (null == currentObject){
            throw new BizException("SYS_MENU_UPDATE_ERROR","当前编号的权限角色不存在!");
        }
        filterChain.doFilter(userRole);
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemRoleDO dataObject = SystemRoleConvertor.toDataObject(userRole);
        dataObject.setUpdater(tokenData.getAccountName());
        dataObject.setUpdateTime(new Date());
        roleMapper.updateUserRole(dataObject);
        roleMapper.deleteMenuRoleByRoleId(userRole.getId());
        for (SystemMenu selectedMenu:userRole.getSystemMenus()) {
            roleMapper.insertMenuRole(dataObject.getId(), selectedMenu.getId());
        }

    }
}
