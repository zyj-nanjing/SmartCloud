package www.bwsensing.com.project.system.database;

import www.bwsensing.com.project.system.domain.ViewMenu;
import java.util.List;

/**
 * @author macos-zyj
 */
public interface ViewMenuMapper {
    /**
     * 查询系统路由配置
     *
     * @param id 系统路由配置主键
     * @return 系统路由配置
     */
    ViewMenu selectViewMenuById(Integer id);

    /**
     * 查询系统路由配置列表
     *
     * @param viewMenu 系统路由配置
     * @return 系统路由配置集合
     */
    List<ViewMenu> selectViewMenuList(ViewMenu viewMenu);

    /**
     * 根据角色获取当前权限
     * @param roleId
     * @return
     */
    List<ViewMenu> selectViewMenusByRoleId(Integer roleId);
    /**
     * 新增系统路由配置
     *
     * @param viewMenu 系统路由配置
     * @return 结果
     */
    int insertViewMenu(ViewMenu viewMenu);

    /**
     * 修改系统路由配置
     *
     * @param viewMenu 系统路由配置
     * @return 结果
     */
    int updateViewMenu(ViewMenu viewMenu);

    /**
     * 删除系统路由配置
     *
     * @param id 系统路由配置主键
     * @return 结果
     */
    int deleteViewMenuById(Integer id);

    /**
     * 批量删除系统路由配置
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteViewMenuByIds(String[] ids);
}
