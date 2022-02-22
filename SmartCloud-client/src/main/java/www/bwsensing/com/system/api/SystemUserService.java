package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.system.dto.command.UserRegisterCmd;
import www.bwsensing.com.system.dto.command.UserUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.UserInfoCO;

/**
 * @author macos-zyj
 */
public interface SystemUserService {

    /**
     *  注册用户
     * @param saveCmd
     * @return
     */
    Response registerUser(UserRegisterCmd saveCmd);

    /**
     * 修改用户信息
     * @param updateCmd
     * @return
     */
    Response updateUser(UserUpdateCmd updateCmd);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Response deleteUser(Integer userId);

    /**
     * 获取用户信息
     * @return
     */
    SingleResponse<UserInfoCO> getUserInfo();
}
