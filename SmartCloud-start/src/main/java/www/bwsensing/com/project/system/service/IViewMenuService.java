package www.bwsensing.com.project.system.service;

import www.bwsensing.com.dto.clientobject.ViewMenuTreeCO;

import java.util.List;

/**
 * @author macos-zyj
 */
public interface IViewMenuService {
    /**
     * 根据权限获取权限树
     * @return
     */
    List<ViewMenuTreeCO> showViewMenuByAuth();
}
