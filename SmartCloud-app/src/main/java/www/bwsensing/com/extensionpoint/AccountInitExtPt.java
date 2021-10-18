package www.bwsensing.com.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.domain.system.SystemUser;
import www.bwsensing.com.dto.command.UserRegisterCmd;

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
