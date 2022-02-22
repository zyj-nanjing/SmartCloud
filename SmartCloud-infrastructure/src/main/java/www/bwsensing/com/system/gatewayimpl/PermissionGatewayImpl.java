package www.bwsensing.com.system.gatewayimpl;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.convertor.PermissionConvertor;
import www.bwsensing.com.system.convertor.SystemRoleConvertor;
import www.bwsensing.com.domain.system.model.menu.MenuKind;
import www.bwsensing.com.domain.system.gateway.PermissionGateway;
import www.bwsensing.com.domain.system.model.role.Permission;
import www.bwsensing.com.domain.system.model.role.UserRole;
import www.bwsensing.com.system.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.system.gatewayimpl.database.ViewMenuMapper;
import www.bwsensing.com.system.gatewayimpl.database.PermissionMapper;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemUserDO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.ViewMenuDO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.PermissionDO;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class PermissionGatewayImpl implements PermissionGateway {

    @Resource
    private ViewMenuMapper viewMenuMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private SystemUserMapper userMapper;

    @Resource
    private SystemRoleMapper roleMapper;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void synchronizationPermission() {
        ViewMenuDO queryMenu = new ViewMenuDO();
        queryMenu.setMenuKind(MenuKind.API.getKind());
        List<ViewMenuDO> apiViews = viewMenuMapper.selectViewMenuList(queryMenu);
        permissionMapper.clearAll();
        for (ViewMenuDO  view : apiViews){
            PermissionDO saveData = new PermissionDO();
            saveData.setId(view.getId());
            saveData.setName(view.getName());
            saveData.setCode(view.getRoleCode());
            saveData.setUrl(view.getApiPath());
            saveData.setComment(view.getComponent());
            permissionMapper.save(saveData);
        }
        log.info("权限路由同步完成!");
    }

    @Override
    public List<UserRole> listRolesByUserId(Integer userId) {
        SystemUserDO userData = userMapper.selectUserById(userId);
        List<UserRole> roles = new ArrayList<>();
        if(null != userData){
            roles.addAll(SystemRoleConvertor.toDomainCollection(roleMapper.selectRoleByUserId(userId)));
        }
        return roles;
    }

    @Override
    public List<Permission> getPermissionsByRoleId(Integer roleId) {
        List<PermissionDO> permissions = permissionMapper.selectPermissionsByRoleId(roleId);
        return PermissionConvertor.toDomainCollection(permissions);
    }

    private void synchronizationPermissionLinks() {

    }
}
