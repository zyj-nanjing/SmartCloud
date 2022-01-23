package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.extension.BizScenario;
import org.springframework.stereotype.Service;
import www.bwsensing.com.api.SystemUserService;
import www.bwsensing.com.command.UserRegisterCmdExo;
import www.bwsensing.com.command.UserUpdateCmdExo;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.convertor.UserDataCoConvertor;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.user.SystemUser;
import www.bwsensing.com.dto.command.UserRegisterCmd;
import www.bwsensing.com.dto.command.UserUpdateCmd;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.UserInfoCO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@CatchAndLog
@Service
public class ISystemUserServiceImpl implements SystemUserService {
    @Resource
    private UserRegisterCmdExo registerCmdExo;
    @Resource
    private UserUpdateCmdExo userUpdateCmdExo;
    @Resource
    private SystemUserGateway userGateway;
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public Response registerUser(UserRegisterCmd saveCmd) {
        BizScenario scenario = BizScenario.valueOf(saveCmd.getBizId(), BizScenarioCode.USE_CAUSE_REGISTER,saveCmd.getScenario());
        saveCmd.setBizScenario(scenario);
        return registerCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateUser(UserUpdateCmd updateCmd) {
        BizScenario scenario = BizScenario.valueOf(updateCmd.getBizId(), BizScenarioCode.USE_CAUSE_UPDATE,updateCmd.getScenario());
        updateCmd.setBizScenario(scenario);
        return userUpdateCmdExo.execute(updateCmd);
    }

    @Override
    public Response deleteUser(Integer userId) {
        userGateway.deleteUser(userId);
        return Response.buildSuccess();
    }

    @Override
    public SingleResponse<UserInfoCO> getUserInfo() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        return SingleResponse.of(UserDataCoConvertor.domainToClientObject(systemUser));
    }
}
