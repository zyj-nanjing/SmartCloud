package www.bwsensing.com.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.dto.command.RoleSaveCmd;
import www.bwsensing.com.dto.command.RoleUpdateCmd;
import www.bwsensing.com.dto.command.query.SystemRolePageQuery;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
/**
 * @author macos-zyj
 */
public interface IRoleService {

    /**
     * 保存角色
     * @param saveCmd
     * @return
     */
    Response saveRole(RoleSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updateRole(RoleUpdateCmd updateCmd);

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
