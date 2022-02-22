package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.common.command.BaseQuery;
import www.bwsensing.com.system.dto.command.PermissionSaveCmd;
import www.bwsensing.com.system.dto.command.PermissionUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.PermissionCO;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface PermissionService {
    /**
     * 获取所有权限
     * @return
     */
    List<PermissionCO> selectAllPermission();

    /**
     * 删除
     * @param saveCmd
     * @return
     */
    Response savePermission(PermissionSaveCmd saveCmd);

    /**
     * 修改
     * @param updateCmd
     * @return
     */
    Response updatePermission(PermissionUpdateCmd updateCmd);

    /**
     * 权限分页查询
     * @param baseQuery
     * @return
     */
    PageResponse<PermissionCO> selectPermissionPage(BaseQuery baseQuery);

    /**
     * 权限列表展示
     * @return
     */
    MultiResponse<PermissionCO> selectMultiPermission();
}
