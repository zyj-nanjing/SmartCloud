package www.bwsensing.com.system.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.convertor.ViewMenuConvertor;
import www.bwsensing.com.domain.system.gateway.PermissionGateway;
import www.bwsensing.com.domain.system.model.menu.MenuKind;
import www.bwsensing.com.domain.system.model.menu.SystemMenu;
import www.bwsensing.com.common.core.filter.FilterChain;
import www.bwsensing.com.domain.system.gateway.SystemMenuGateway;
import www.bwsensing.com.system.gatewayimpl.database.ViewMenuMapper;
import www.bwsensing.com.common.core.filter.FilterChainFactory;
import www.bwsensing.com.system.filter.ViewNameRepeatFilter;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.ViewMenuDO;
import www.bwsensing.com.system.filter.ViewApiPathRepeatFilter;
import www.bwsensing.com.system.filter.ViewRoleCodeRepeatFilter;

/**
 * @author macos-zyj
 */
@Slf4j
@Component
public class SystemMenuGatewayImpl implements SystemMenuGateway{

    private static final FilterChain<SystemMenu> FILTER_CHAIN = FilterChainFactory.buildFilterChain(
            ViewNameRepeatFilter.class,
            ViewApiPathRepeatFilter.class,
            ViewRoleCodeRepeatFilter.class
    );

    @Resource
    private ViewMenuMapper viewMenuMapper;

    @Resource
    private PermissionGateway permissionGateway;

    @Override
    public void addSystemMenu(SystemMenu viewMenu) {
        FILTER_CHAIN.doFilter(viewMenu);
        ViewMenuDO saveData = ViewMenuConvertor.toDataObject(viewMenu);
        viewMenuMapper.insertViewMenu(saveData);
        if(viewMenu.getMenuKind().equals(MenuKind.API)){
            permissionGateway.synchronizationPermission();
        }
    }


    @Override
    public void updateSystemMenu(SystemMenu viewMenu) {
        ViewMenuDO currentMenu = viewMenuMapper.selectViewMenuById(viewMenu.getId());
        if (null == currentMenu){
            throw new BizException("SYS_MENU_UPDATE_ERROR","当前编号的系统菜单不存在!");
        }
        FILTER_CHAIN.doFilter(viewMenu);
        ViewMenuDO updateData = ViewMenuConvertor.toDataObject(viewMenu);
        viewMenuMapper.updateViewMenu(updateData);
        if(viewMenu.getMenuKind().equals(MenuKind.API)){
            permissionGateway.synchronizationPermission();
        }
    }


    @Override
    public List<SystemMenu> querySystemMenusByIds(List<Integer> ids) {
        List<ViewMenuDO> viewCollection = viewMenuMapper.selectViewMenusInIds(ids);
        return ViewMenuConvertor.toDomainArray(viewCollection);
    }

}
