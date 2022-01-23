package www.bwsensing.com.domain.gateway;

import www.bwsensing.com.domain.system.menu.SystemMenu;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface SystemMenuGateway {
    /**
     * 添加系统菜单
     * @param viewMenu
     */
    void addSystemMenu(SystemMenu viewMenu);

    /**
     * 修改系统菜单
     * @param viewMenu
     */
    void updateSystemMenu(SystemMenu viewMenu);

    /**
     * 根据获取的Id序列获取系统菜单集合
     * @param ids
     * @return
     */
    List<SystemMenu>  querySystemMenusByIds(List<Integer> ids);
}
