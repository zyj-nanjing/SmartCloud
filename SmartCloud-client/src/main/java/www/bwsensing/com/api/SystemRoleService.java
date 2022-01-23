package www.bwsensing.com.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.dto.command.SystemRoleSaveCmd;
import www.bwsensing.com.dto.command.SystemRoleUpdateCmd;
import www.bwsensing.com.dto.command.query.SystemRolePageQuery;
/**
 * @author macos-zyj
 */
public interface SystemRoleService {
    /**
     * 保存角色
     * @param saveCmd
     * @return
     */
    Response saveRole(SystemRoleSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateRole(SystemRoleUpdateCmd updateCmd);

    /**
     * 根据角色编号获取角色信息
     * @param roleId
     * @return
     */
    SingleResponse<SystemRoleCO> queryRoleInfoById(Integer roleId);
    /**
     * 查询所有根节点角色
     * @return
     */
    MultiResponse<SystemRoleCO> selectRootRoles();

    /**
     * 系统角色分页查询
     * @param query
     * @return
     */
    PageResponse<SystemRoleCO> querySystemRolePage(SystemRolePageQuery query);
}
