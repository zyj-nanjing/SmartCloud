package www.bwsensing.com.system.api;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.system.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.system.dto.clientobject.ViewMenuTreeCO;
import www.bwsensing.com.system.dto.command.SystemMenuSaveCmd;
import www.bwsensing.com.system.dto.command.SystemMenuUpdateCmd;

/**
 * @author macos-zyj
 */
public interface SystemMenuService {

    /**
     * 根据菜单编号获取菜单详情
     * @param mid
     * @return
     */
    SingleResponse<SystemMenuCO> querySystemMenuById(Integer mid);
    /**
     *
     * 根据权限获取权限树
     * @return
     */
    MultiResponse<ViewMenuTreeCO> showViewMenuByAuth();

    /**
     * 获取系统菜单目录树
     * @return
     */
    MultiResponse<SystemMenuCO> showSystemMenus();

    /**
     * 添加系统菜单
     * @param saveCmd
     * @return
     */
    Response addSystemMenu(SystemMenuSaveCmd saveCmd);

    /**
     * 修改系统菜单
     * @param updateCmd
     * @return
     */
    Response updateSystemMenu(SystemMenuUpdateCmd updateCmd);
}
