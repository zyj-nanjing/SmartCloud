package www.bwsensing.com.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.UserLoginCmd;

/**
 * @author macos-zyj
 */
public interface IUserAuthService {
    /**
     * 用户登录
     * @param loginCmd
     * @return
     */
    SingleResponse<String> login(UserLoginCmd loginCmd);

    /**
     * 登出
     * @return
     */
    Response loginOut();

    /**
     * 获取Redis中的Token Key
     * @param accountName
     * @return
     */
    String getTokenCheckKey(String accountName);
}
