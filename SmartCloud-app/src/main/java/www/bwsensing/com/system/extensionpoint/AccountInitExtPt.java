package www.bwsensing.com.system.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.system.dto.command.UserRegisterCmd;

/**
 * @author macos-zyj
 */
public interface AccountInitExtPt extends ExtensionPointI {
    /**
     * 初始化账户信息
     * @param registerCmd 注册事件
     * @return 初始化用户信息
     */
    SystemUser initAccount(UserRegisterCmd registerCmd);
}
