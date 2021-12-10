package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.SystemUser;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemUserGateway {
    /**
     * 由账户名称获取用户信息
     * @param accountName
     * @return
     */
    SystemUser loadUserByAccountName(String accountName);

    /**
     * 是否有权限添加对应权限的账户
     * @param groupId 对应操作组
     * @param isAdmin 是否为超管
     * @return
     */
    Boolean haveRoleToAddUser(Integer groupId,Boolean isAdmin);

    /**
     * 根据用户编号获取用户信息及权限
     * @param userId
     * @return
     */
    SystemUser getUserInfoContainRole(Integer userId);

    /**
     * 获取当前分组下的所有用户
     * @return
     */
    List<SystemUser> selectUserByUserGroup();

    /**
     * 获取用户角色
     * @param accountName
     * @return
     */
    String getUserRole(String accountName);

    /**
     * 用户校验
     * @param accountName
     * @param workNumber
     * @param groupId
     */
    void validateUserRegister(String accountName,String workNumber,Integer groupId);

    /**
     * 注册
     * @param systemUser
     */
    void registerUser(SystemUser systemUser);

    /**
     * 修改用户信息
     * @param systemUser
     */
    void updateUser(SystemUser systemUser);

    /**
     * 删除
     * @param uid
     */
    void deleteUser(Integer uid);
}
