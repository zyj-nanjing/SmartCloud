package www.bwsensing.com.gatewayimpl.database;

import org.apache.ibatis.annotations.Param;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemUserMapper {
    /**
     * 查询未添加的小组成员
     * @param pid
     * @param groupId
     * @return
     */
    List<SystemUserDO> selectUserByPidAndGroupId(@Param("pid")Integer pid,@Param("groupId") Integer groupId);
    /**
     * 获取当前分组的所有用户
     * @param groupId
     * @return
     */
    List<SystemUserDO> selectUserByGroupId(Integer groupId);
    /**
     * 账户名称
     * @param accountName
     * @return
     */
    SystemUserDO selectUserByAccountName(String accountName);

    /**
     * 根据用户编号获取信息
     * @param id
     * @return
     */
    SystemUserDO selectUserById(Integer id);
    /**
     * 校验工号数量
     * @param workNumber
     * @param groupId
     * @return
     */
    Integer selectWorkNumberGroup(@Param("workNumber")String workNumber,@Param("groupId") Integer groupId);

    /***
     * 获取用户角色
     * @param accountName
     * @return
     */
    String selectUserRole(String accountName);

    /**
     * 校验工号数量
     * @param workNumber
     * @return
     */
    Integer selectWorkNumber(String workNumber);

    /**
     * 保存
     * @param systemUserDo
     */
    void save(SystemUserDO systemUserDo);

    /**
     * 修改
     * @param systemUser
     */
    void update(SystemUserDO systemUser);

    /***
     * 删除用户
     * @param id
     */
    void delete(Integer id);

    /**
     * 禁用账号
     * @param id
     */
    void banUser(Integer id);
}
