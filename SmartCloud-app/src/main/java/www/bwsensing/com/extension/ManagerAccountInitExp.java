package www.bwsensing.com.extension;

import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.dto.command.UserRegisterCmd;
import www.bwsensing.com.extensionpoint.AccountInitExtPt;
import www.bwsensing.com.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.OperateGroupDO;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.USE_CAUSE_REGISTER,scenario = BizScenarioCode.MANAGER_SCENARIO)
public class ManagerAccountInitExp implements AccountInitExtPt {
    @Resource
    private SystemUserGateway systemUserGateway;
    @Resource
    private OperateGroupMapper groupMapper;

    @Override
    public SystemUser initAccount(UserRegisterCmd registerCmd) {
        OperateGroupDO groupDo = groupMapper.selectGroupById(registerCmd.getGroupId());
        Assert.notNull(groupDo,"操作组不存在");
        systemUserGateway.validateUserRegister(registerCmd.getAccountName(), registerCmd.getWorkNumber(),groupDo.getId());
        SystemUser registerUser = new SystemUser();
        BeanUtils.copyProperties(registerCmd, registerUser);
        registerCmd.setGroupId(groupDo.getId());
        if(registerCmd.getIsAdmin()&&groupDo.getIsInner()){
            registerUser.setRole(RoleConstant.ROOT_ADMIN);
            log.info("业务线:{},功能模块:{},行为:{}", BizScenarioCode.BIZ_ID_CLOUD, "超级管理员", "注册-账户初始化");
        } else{
            registerUser.setRole(RoleConstant.GROUP_ADMIN);
            log.info("业务线:{},功能模块:{},行为:{}", BizScenarioCode.BIZ_ID_CLOUD, "组内管理员", "注册-账户初始化");
        }
        return registerUser;
    }
}
