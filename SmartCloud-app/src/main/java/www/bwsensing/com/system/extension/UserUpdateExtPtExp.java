package www.bwsensing.com.system.extension;

import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.system.dto.command.UserUpdateCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.system.extensionpoint.UserUpdateExtPt;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.USE_CAUSE_REGISTER,scenario = BizScenarioCode.USER_SCENARIO)
public class UserUpdateExtPtExp implements UserUpdateExtPt {
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public void validateUserUpdate(UserUpdateCmd updateCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        log.info("人员信息修改-----> 小组编号:{} 用户编号:{}",tokenData.getGroupId(),updateCmd.getId());
        if (!tokenData .getUserId().equals(updateCmd.getId())){
            throw new BizException("USER_UPDATE_VALID_ERROR","非管理员只能修改个人信息");
        }
        if (StringUtils.isNotEmpty(updateCmd.getRole())){
            throw new BizException("USER_UPDATE_VALID_ERROR","非管理员无权修改角色");
        }
        if (StringUtils.isNotEmpty(updateCmd.getPosition())){
            throw new BizException("USER_UPDATE_VALID_ERROR","非管理员无权修改职位");
        }
    }
}
