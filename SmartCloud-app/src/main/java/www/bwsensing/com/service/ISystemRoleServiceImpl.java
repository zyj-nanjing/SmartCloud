package www.bwsensing.com.service;

import java.util.List;
import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.SystemRoleService;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.role.UserRole;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.dto.command.SystemRoleSaveCmd;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.convertor.SystemRoleConvertor;
import www.bwsensing.com.convertor.SystemRoleCoConvertor;
import www.bwsensing.com.dto.command.SystemRoleUpdateCmd;
import www.bwsensing.com.convertor.SystemMenuCoConvertor;
import www.bwsensing.com.domain.gateway.SystemMenuGateway;
import www.bwsensing.com.domain.gateway.SystemRoleGateway;
import www.bwsensing.com.gatewayimpl.database.ViewMenuMapper;
import www.bwsensing.com.dto.command.query.SystemRolePageQuery;
import www.bwsensing.com.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class ISystemRoleServiceImpl implements SystemRoleService {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemRoleMapper roleMapper;
    @Resource
    private ViewMenuMapper viewMenuMapper;
    @Resource
    private SystemRoleGateway systemRoleGateway;
    @Resource
    private SystemMenuGateway systemMenuGateway;

    @Override
    public Response saveRole(SystemRoleSaveCmd saveCmd) {
        filterValidUser();
        SystemRoleDO saveRole = new SystemRoleDO();
        BeanUtils.copyProperties(saveCmd,saveRole);
        UserRole userRole = SystemRoleConvertor.toDomain(saveRole);
        userRole.setSystemMenus(systemMenuGateway.querySystemMenusByIds(saveCmd.getMenuIds()));
        systemRoleGateway.saveSystemRole(userRole);
        return Response.buildSuccess();
    }

    @Override
    public Response updateRole(SystemRoleUpdateCmd updateCmd) {
        filterValidUser();
        SystemRoleDO updateRole = new SystemRoleDO();
        BeanUtils.copyProperties(updateCmd,updateRole);
        UserRole userRole = SystemRoleConvertor.toDomain(updateRole);
        userRole.setSystemMenus(systemMenuGateway.querySystemMenusByIds(updateCmd.getMenuIds()));
        systemRoleGateway.updateSystemRole(userRole);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<SystemRoleCO> queryRoleInfoById(Integer roleId) {
        SystemRoleDO currentRole = roleMapper.getUserRoleById(roleId);
        if ( null != currentRole){
            List<ViewMenuDO> menus = viewMenuMapper.selectViewMenusByRoleId(roleId);
            SystemRoleCO clientObject = SystemRoleCoConvertor.toClientObject(currentRole);
            List<SystemMenuCO> menuCollection = SystemMenuCoConvertor.initMenuTree(menus);
            clientObject.setSystemMenus(menuCollection);
            return SingleResponse.of(clientObject);
        }
        throw new BizException("ROLE_NOT_FOUND","当前角色编号对应的角色不存在!");
    }

    @Override
    public MultiResponse<SystemRoleCO> selectRootRoles() {
        SystemRoleDO queryRole = new SystemRoleDO();
        List<SystemRoleDO> selectResult = roleMapper.selectRoleBySort(queryRole);
        return MultiResponse.of(SystemRoleCoConvertor.toClientObjectArray(selectResult));
    }

    @Override
    public PageResponse<SystemRoleCO> querySystemRolePage(SystemRolePageQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        SystemRoleDO queryRole = new SystemRoleDO();
        BeanUtils.copyProperties(query,queryRole);
        List<SystemRoleDO> resultList = roleMapper.selectRoleBySort(queryRole);
        PageInfo<SystemRoleDO> pageInfo = new PageInfo<>(resultList);
        List<SystemRoleCO> result = SystemRoleCoConvertor.toClientObjectArray(resultList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),query.getPageIndex() );
    }

    private void filterValidUser(){
        TokenData tokenData = tokenGateway.getTokenInfo();
        if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            throw new BizException("AUTH_NOT_ALLOW","非超级管理员用户无权配置");
        }
    }
}
