package www.bwsensing.com.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.extension.ExtensionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.dto.command.UserRegisterCmd;
import www.bwsensing.com.extensionpoint.AccountInitExtPt;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class UserRegisterCmdExo {
    @Resource
    private ExtensionExecutor extensionExecutor;
    @Resource
    private SystemUserGateway systemUserGateway;

    public Response execute(UserRegisterCmd registerCmd){
        //Convert CO to Entity
        SystemUser userEntity = extensionExecutor.execute(AccountInitExtPt.class, registerCmd.getBizScenario(), extension -> extension.initAccount(registerCmd));
        Assert.notNull(userEntity,"账号初始化不能为空!");
        systemUserGateway.registerUser(userEntity);
        log.info("End processing command:" + registerCmd.getAccountName());
        return Response.buildSuccess();
    }
}
