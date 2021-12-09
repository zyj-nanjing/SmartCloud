package www.bwsensing.com.extension;

import com.alibaba.cola.extension.Extension;
import lombok.extern.slf4j.Slf4j;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.command.UserUpdateCmd;
import www.bwsensing.com.extensionpoint.UserUpdateExtPt;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,useCase = BizScenarioCode.USE_CAUSE_UPDATE,scenario = BizScenarioCode.ROOT_MANAGER_SCENARIO)
public class RootUpdateUserExtPtExp implements UserUpdateExtPt {

    @Resource
    private TokenGateway tokenGateway;

    @Override
    public void validateUserUpdate(UserUpdateCmd updateCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        log.info("小组管理员 人员信息修改-----> 管理员:{}  小组编号:{} 被修改用户编号:{}",tokenData.getAccountName(),tokenData.getGroupId(),updateCmd.getId());
    }
}
