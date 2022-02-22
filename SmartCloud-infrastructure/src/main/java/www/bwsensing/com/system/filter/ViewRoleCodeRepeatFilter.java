package www.bwsensing.com.system.filter;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.core.filter.Filter;
import www.bwsensing.com.common.core.filter.FilterInvoker;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.system.model.menu.SystemMenu;
import www.bwsensing.com.system.gatewayimpl.database.ViewMenuMapper;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.ViewMenuDO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
public class ViewRoleCodeRepeatFilter implements Filter<SystemMenu> {

    @Resource
    private ViewMenuMapper viewMenuMapper;

    @Override
    public void doFilter(SystemMenu context, FilterInvoker nextFilter) {
        if (null == context.getId()){
            validateFilter(context);
        } else {
            ViewMenuDO currentView = viewMenuMapper.selectViewMenuById(context.getId());
            if (StringUtils.isNotEmpty(currentView.getRoleCode())){
                if (!currentView.getRoleCode().equals(context.getRoleCode())){
                    validateFilter(context);
                }
            }
        }
        nextFilter.invoke(context);
    }

    private void validateFilter(SystemMenu context){
        ViewMenuDO queryData= new ViewMenuDO();
        queryData.setRoleCode(context.getRoleCode());
        List<ViewMenuDO> menuList = viewMenuMapper.selectViewMenuList(queryData);
        if (menuList.size() > 0 ){
            throw new BizException("VIEW_MENU_ADD_OR_EDIT_ERROR","当前权限编码已存在!");
        }
    }
}
