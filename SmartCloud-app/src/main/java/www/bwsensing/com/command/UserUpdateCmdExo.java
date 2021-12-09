package www.bwsensing.com.command;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import com.alibaba.cola.extension.ExtensionExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.dto.command.UserUpdateCmd;
import www.bwsensing.com.extensionpoint.UserUpdateExtPt;
import www.bwsensing.com.gatewayimpl.database.OperateGroupMapper;

import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class UserUpdateCmdExo {
    @Resource
    private SystemUserGateway systemUserGateway;
    @Resource
    private ExtensionExecutor extensionExecutor;
    @Resource
    private OperateGroupMapper operateGroupMapper;

    public Response execute(UserUpdateCmd updateCmd){
        //扩展点 多权限校验
        extensionExecutor.executeVoid(UserUpdateExtPt.class, updateCmd.getBizScenario(), extension -> extension.validateUserUpdate(updateCmd));
        if(null != updateCmd.getGroupId()){
            if ( null == operateGroupMapper.selectGroupById(updateCmd.getGroupId())){
                throw new BizException("USER_UPDATE_VALID_ERROR","小组编号不正确!");
            }
        }
        SystemUser updateUser= new SystemUser();
        BeanUtils.copyProperties(updateCmd,updateUser);
        systemUserGateway.updateUser(updateUser);
        log.info("人员信息修改---------> 用户修改成功");
        return Response.buildSuccess();
    }
}
