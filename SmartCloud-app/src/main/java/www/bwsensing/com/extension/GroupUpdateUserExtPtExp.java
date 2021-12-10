package www.bwsensing.com.extension;

import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.command.UserUpdateCmd;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.extensionpoint.UserUpdateExtPt;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.USE_CAUSE_UPDATE,scenario = BizScenarioCode.GROUP_MANAGER_SCENARIO)
public class GroupUpdateUserExtPtExp implements UserUpdateExtPt {
    @Resource
    private SystemUserMapper userMapper;

    @Resource
    private TokenGateway tokenGateway;

    @Override
    public void validateUserUpdate(UserUpdateCmd updateCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUserDO userDo = userMapper.selectUserById(updateCmd.getId());
        log.info("小组管理员 人员信息修改-----> 管理员:{}  小组编号:{} 被修改用户编号:{}",tokenData.getAccountName(),tokenData.getGroupId(),updateCmd.getId());
        if(!userDo.getOperateGroupId().equals(tokenData.getGroupId()) ){
            throw new BizException("USER_UPDATE_VALID_ERROR","该用户不属于当前分组无权修改");
        }
        if (!userDo.getOperateGroupId().equals(updateCmd.getGroupId())){
            throw new BizException("USER_UPDATE_VALID_ERROR","非超级管理员无权转移用户到别的分组");
        }
    }
}
