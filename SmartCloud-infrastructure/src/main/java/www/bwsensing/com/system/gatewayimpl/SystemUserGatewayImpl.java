package www.bwsensing.com.system.gatewayimpl;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.device.gatewayimpl.database.NotificationMemberMapper;
import www.bwsensing.com.system.convertor.SystemUserConvertor;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.OperateGroupDO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemUserDO;
import www.bwsensing.com.project.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.system.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemUserMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class SystemUserGatewayImpl implements SystemUserGateway {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private OperateGroupMapper groupMapper;
    @Resource
    private NotificationMemberMapper memberMapper;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private SystemRoleMapper roleMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public SystemUser loadUserByAccountName(String accountName) {
        SystemUserDO selectedUser = systemUserMapper.selectUserByAccountName(accountName);
        if(null != selectedUser){
            SystemUser resultUser =  SystemUserConvertor.toDomain(selectedUser);
            resultUser.setAccountNonExpired(true);
            return resultUser;
        } else{
            return new SystemUser();
        }
    }

    @Override
    public Boolean haveRoleToAddUser(Integer groupId,Boolean isAdmin) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        if(RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
            return true;
        } else{
            if(!isAdmin){
                return RoleConstant.GROUP_ADMIN.equals(tokenData.getRole()) && tokenData.getGroupId().equals(groupId);
            }
        }
        return false;
    }

    @Override
    public SystemUser getUserInfoContainRole(Integer userId) {
        Assert.notNull(userId,"??????????????????");
        SystemUserDO selectedUser = systemUserMapper.selectUserById(userId);
        return SystemUserConvertor.toDomain(selectedUser);
    }

    @Override
    public List<SystemUser> selectUserByUserGroup() {
        return null;
    }

    @Override
    public String getUserRole(String accountName) {
        return systemUserMapper.selectUserRole(accountName);
    }

    @Override
    public void validateUserRegister(String accountName,String workNumber,Integer groupId) {
        if (StringUtils.isNotEmpty(loadUserByAccountName(accountName).getAccountName())){
            throw new BizException("USER_HAS_EXIST","???????????????");
        } else{
            OperateGroupDO groupDo = groupMapper.selectGroupById(groupId);
            if(groupDo.getIsInner()){
                if (systemUserMapper.selectWorkNumber(workNumber)>0){
                    throw new BizException("WORK_NUMBER_HAS_EXIST","??????????????????????????????");
                }
            } else{
                if (systemUserMapper.selectWorkNumberGroup(workNumber,groupId)>0){
                    throw new BizException("WORK_NUMBER_HAS_EXIST","?????????????????????????????????");
                }
            }
        }
    }

    @Override
    public void registerUser(SystemUser systemUser) {
        SystemUserDO userDo = SystemUserConvertor.toDataObject(systemUser);
        userDo.setRoleId(roleMapper.getUserRoleByCode(systemUser.getRole()).getId());
        systemUserMapper.save(userDo);
    }

    @Override
    public void updateUser(SystemUser systemUser) {
        SystemUserDO userDo = SystemUserConvertor.toDataObject(systemUser);
        systemUserMapper.update(userDo);
    }

    @Override
    public void deleteUser(Integer uid) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUserDO userDo = systemUserMapper.selectUserById(uid);
        if(null == userDo){
            throw new BizException("NO_USER_FOUND","??????????????????!");
        }
        OperateGroupDO operateGroupDo = groupMapper.selectGroupById(userDo.getOperateGroupId());
        if(RoleConstant.USER.equals(tokenData.getRole()) ){
            throw new BizException("NO_PERMISSION_DELETE","??????????????????!");
        }
        if (operateGroupDo.getIsInner()){
            if (!RoleConstant.ROOT_ADMIN.equals(tokenData.getRole())){
                throw new BizException("NO_PERMISSION_DELETE","??????????????????!");
            }
        } else {
            if (userDo.getRole().equals(tokenData.getRole())){
                throw new BizException("NO_PERMISSION_DELETE","??????????????????!");
            }
        }
        if(projectMapper.countUserOwner(uid)>0){
            throw new BizException("USER_PROJECT_IN_USER","??????????????????????????????????????????!");
        }
        systemUserMapper.delete(uid);
        projectMapper.deleteMemberByUserId(uid);
        memberMapper.deleteByUserId(uid);
    }
}
