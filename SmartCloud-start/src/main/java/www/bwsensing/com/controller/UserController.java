package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.ISystemUserService;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.SystemUserGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.dto.clientobject.ViewMenuTreeCO;
import www.bwsensing.com.dto.command.UserRegisterCmd;
import www.bwsensing.com.dto.command.UserUpdateCmd;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.UserInfoCO;
import www.bwsensing.com.project.system.service.IViewMenuService;
import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Slf4j
@CrossOrigin
@Validated
@RequestMapping("/api/v1.0/open/user")
@RestController
public class UserController {
    @Autowired
    private ISystemUserService userService;
    @Resource
    private SystemUserGateway systemUserGateway;
    @Autowired
    private IViewMenuService viewMenuService;
    /**
     * 用于密码加解密
     */
    @SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private TokenGateway tokenGateway;

    @GetMapping("/menu")
    public MultiResponse<ViewMenuTreeCO> getViewMenus(){
        return MultiResponse.of(viewMenuService.showViewMenuByAuth());
    }

    @PostMapping("/save/user")
    public Response registerUser(@Valid @RequestBody UserRegisterCmd saveCmd){
        if (systemUserGateway.haveRoleToAddUser(saveCmd.getGroupId(),false)){
            saveCmd.setBizId(BizScenarioCode.BIZ_ID_CLOUD);
            saveCmd.setScenario(BizScenarioCode.USER_SCENARIO);
            saveCmd.setPassword(passwordEncoder.encode(saveCmd.getPassword()));
            return userService.registerUser(saveCmd);
        } else{
            log.warn("No Permission To Create User");
            return Response.buildFailure("USER_ADD_ERROR","无权限添加用户");
        }
    }

    @PostMapping("/save/manager")
    public Response registerManager(@Valid @RequestBody UserRegisterCmd saveCmd){
        if (systemUserGateway.haveRoleToAddUser(saveCmd.getGroupId(),true)){
            saveCmd.setBizId(BizScenarioCode.BIZ_ID_CLOUD);
            saveCmd.setScenario(BizScenarioCode.MANAGER_SCENARIO);
            saveCmd.setPassword(passwordEncoder.encode(saveCmd.getPassword()));
            return userService.registerUser(saveCmd);
        } else{
            log.warn("No Permission To Create Manager");
            return Response.buildFailure("USER_ADD_ERROR","无权限添加管理员");
        }
    }
    @GetMapping("/delete/{userId}")
    public Response deleteUser(@PathVariable Integer userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/update")
    public Response updateUser(@Valid @RequestBody UserUpdateCmd updateCmd){
        TokenData tokenData = tokenGateway.getTokenInfo();
        updateCmd.setBizId(BizScenarioCode.BIZ_ID_CLOUD);
        updateCmd.setScenario(BizScenarioCode.getOperateRegisterScenario(tokenData.getRole()));
        if(StringUtils.isNotEmpty(updateCmd.getPassword())){
            updateCmd.setPassword(passwordEncoder.encode(updateCmd.getPassword()));
        }
        return userService.updateUser(updateCmd);
    }

    @GetMapping("/info")
    public SingleResponse<UserInfoCO> getUserInfo(){
        return userService.getUserInfo();
    }
}
