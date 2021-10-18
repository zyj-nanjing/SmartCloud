package www.bwsensing.com.extensionpoint;

import com.alibaba.cola.extension.ExtensionPointI;
import www.bwsensing.com.dto.command.UserUpdateCmd;
/**
 * 用户修改扩展点
 * @author macos-zyj
 */
public interface UserUpdateExtPt extends ExtensionPointI {
    /**
     * 校验用户修改接口
     * @param updateCmd
     */
    void validateUserUpdate(UserUpdateCmd updateCmd);
}
