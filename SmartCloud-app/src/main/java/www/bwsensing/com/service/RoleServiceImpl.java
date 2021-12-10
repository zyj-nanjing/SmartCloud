package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IRoleService;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.SystemRoleCoConvertor;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.command.RoleSaveCmd;
import www.bwsensing.com.dto.command.RoleUpdateCmd;
import www.bwsensing.com.dto.command.query.SystemRolePageQuery;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.gatewayimpl.database.RoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.RoleDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class RoleServiceImpl implements IRoleService {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public Response saveRole(RoleSaveCmd saveCmd) {
        filterValidUser();
        RoleDO saveRole = new RoleDO();
        BeanUtils.copyProperties(saveCmd,saveRole);
        validRole(saveRole);
        roleMapper.saveUserRole(saveRole);
        return Response.buildSuccess();
    }

    @Override
    public Response updateRole(RoleUpdateCmd updateCmd) {
        filterValidUser();
        RoleDO updateRole = new RoleDO();
        BeanUtils.copyProperties(updateCmd,updateRole);
        validRole(updateRole);
        roleMapper.updateUserRole(updateRole);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<SystemRoleCO> selectRootRoles() {
        RoleDO queryRole = new RoleDO();
        queryRole.setIsBaseRole(true);
        List<RoleDO> selectResult = roleMapper.selectRoleBySort(queryRole);
        return MultiResponse.of(SystemRoleCoConvertor.toClientObjectArray(selectResult));
    }

    @Override
    public PageResponse<SystemRoleCO> querySystemRolePage(SystemRolePageQuery query) {
        PageHelper.startPage(query.getPageIndex(), query.getPageSize());
        RoleDO queryRole = new RoleDO();
        BeanUtils.copyProperties(query,queryRole);
        List<RoleDO> resultList = roleMapper.selectRoleBySort(queryRole);
        PageInfo<RoleDO> pageInfo = new PageInfo<>(resultList);
        List<SystemRoleCO> result = SystemRoleCoConvertor.toClientObjectArray(resultList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),query.getPageIndex() );
    }

    private void filterValidUser(){
        TokenData tokenData = tokenGateway.getTokenInfo();
        if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            throw new BizException("AUTH_NOT_ALLOW","非超级管理员用户无权配置");
        }
    }

    private void  validRole(RoleDO role){
        if (StringUtils.isNotEmpty(role.getRoleCode())){
            RoleDO selectRole = roleMapper.getUserRoleByCode(role.getRoleCode());
            if (null != selectRole){
                if(null != role.getId()){
                    if (role.getRoleName().equals(selectRole.getRoleCode())){
                        return;
                    }
                }
                throw new BizException("ROLE_VALID_ERROR","角色编号重复!");
            }
        }
        if (null != role.getIsBaseRole()){
            if (role.getIsBaseRole()&&StringUtils.isNotEmpty(role.getBaseRoleCode())){
                throw new BizException("ROLE_VALID_ERROR","根节点角色无需添加根节点编码!");
            }
        }
        if (StringUtils.isNotEmpty(role.getBaseRoleCode())){
            if (null == roleMapper.getUserRoleByCode(role.getBaseRoleCode())){
                throw new BizException("ROLE_VALID_ERROR","根节点角色不存在!");
            }
        }
    }

}
