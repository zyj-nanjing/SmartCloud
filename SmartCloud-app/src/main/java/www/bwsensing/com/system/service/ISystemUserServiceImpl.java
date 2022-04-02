package www.bwsensing.com.system.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.SysException;
import com.alibaba.cola.extension.BizScenario;
import org.springframework.stereotype.Service;
import www.bwsensing.com.common.clientobject.RSAKeyCO;
import www.bwsensing.com.common.utills.RSAUtils;
import www.bwsensing.com.system.api.SystemUserService;
import www.bwsensing.com.system.command.UserRegisterCmdExo;
import www.bwsensing.com.system.command.UserUpdateCmdExo;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.system.convertor.UserDataCoConvertor;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.system.dto.command.UserRegisterCmd;
import www.bwsensing.com.system.dto.command.UserUpdateCmd;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.system.dto.clientobject.UserInfoCO;
import javax.annotation.Resource;
import java.util.Map;

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


    @Override
    public SingleResponse<RSAKeyCO> getAccountRsaKey() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = userGateway.getUserInfoContainRole(tokenData.getUserId());
        RSAKeyCO keyResponse = new RSAKeyCO();
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            keyResponse.setPublicKey(RSAUtils.getPublicKey(keyMap));
            keyResponse.setPrivateKey(RSAUtils.getPrivateKey(keyMap));
            systemUser.setPublicKey(RSAUtils.getPublicKey(keyMap));
            userGateway.updateUser(systemUser);
            return SingleResponse.of(keyResponse);
        } catch (Exception ex){
            throw new SysException("ras init error");
        }
    }
}
