package www.bwsensing.com.gatewayimpl.database;

import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;
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
    ViewMenuDO selectViewMenuById(Integer id);

    /**
     * 获取所有路由
     * @return
     */
    List<ViewMenuDO> getMenuList();

    /**
     * 查询系统路由配置列表
     *
     * @param viewMenu 系统路由配置
     * @return 系统路由配置集合
     */
    List<ViewMenuDO> selectViewMenuList(ViewMenuDO viewMenu);

    /**
     * 根据角色获取当前权限
     * @param roleId
     * @return
     */
    List<ViewMenuDO> selectViewMenusByRoleId(Integer roleId);

    /**
     * 获取对应序列的系统菜单
     * @param ids
     * @return
     */
    List<ViewMenuDO> selectViewMenusInIds(List<Integer> ids);

    /**
     * 新增系统路由配置
     *
     * @param viewMenu 系统路由配置
     * @return 结果
     */
    int insertViewMenu(ViewMenuDO viewMenu);

    /**
     * 修改系统路由配置
     *
     * @param viewMenu 系统路由配置
     * @return 结果
     */
    int updateViewMenu(ViewMenuDO viewMenu);

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
