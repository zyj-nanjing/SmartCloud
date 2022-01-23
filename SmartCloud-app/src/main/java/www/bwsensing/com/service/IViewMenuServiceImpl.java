package www.bwsensing.com.service;

import java.util.*;

import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.SystemMenuService;
import www.bwsensing.com.domain.gateway.SystemMenuGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.menu.MenuKind;
import www.bwsensing.com.domain.system.menu.SystemMenu;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.dto.clientobject.ViewMenuTreeCO;
import www.bwsensing.com.dto.command.SystemMenuSaveCmd;
import www.bwsensing.com.dto.command.SystemMenuUpdateCmd;
import www.bwsensing.com.gatewayimpl.database.SystemRoleMapper;
import www.bwsensing.com.convertor.SystemMenuCoConvertor;
import www.bwsensing.com.gatewayimpl.database.ViewMenuMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;
import static www.bwsensing.com.convertor.ViewMenuCoConvertor.initViewMenuTree;


/**
 * @author macos-zyj
 */
@Slf4j
@CatchAndLog
@Component
public class IViewMenuServiceImpl implements SystemMenuService {
    @Resource
    private ViewMenuMapper menuMapper;
    @Resource
    private SystemRoleMapper roleMapper;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private SystemMenuGateway systemMenuGateway;

    @Override
    public SingleResponse<SystemMenuCO> querySystemMenuById(Integer mid) {
        ViewMenuDO dataObject = menuMapper.selectViewMenuById(mid);
        return SingleResponse.of(SystemMenuCoConvertor.toViewMenuCo(dataObject));
    }

    @Override
    public MultiResponse<ViewMenuTreeCO> showViewMenuByAuth() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemRoleDO role = roleMapper.getUserRoleByCode(tokenData.getRole());
        Assert.notNull(role,"当前角色不存在!");
        List<ViewMenuDO> dataCollection = menuMapper.selectViewMenusByRoleId(role.getId());
        return MultiResponse.of(initViewMenuTree(dataCollection));
    }

    @Override
    public MultiResponse<SystemMenuCO> showSystemMenus() {
        List<ViewMenuDO> dataCollection = menuMapper.selectViewMenuList(new ViewMenuDO());
        return MultiResponse.of(SystemMenuCoConvertor.initMenuTree(dataCollection));
    }

    @Override
    public Response addSystemMenu(SystemMenuSaveCmd saveCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        saveCmd.setCreator(tokenData.getUserName());
        saveCmd.setCreateTime(new Date());
        SystemMenu domain = new SystemMenu();
        BeanUtils.copyProperties(saveCmd,domain);
        domain.setMenuKind(MenuKind.getMenuKind(saveCmd.getMenuKind()));
        validate(domain);
        systemMenuGateway.addSystemMenu(domain);
        return Response.buildSuccess();
    }

    @Override
    public Response updateSystemMenu(SystemMenuUpdateCmd updateCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        updateCmd.setUpdate(tokenData.getUserName());
        updateCmd.setUpdateTime(new Date());
        SystemMenu domain = new SystemMenu();
        BeanUtils.copyProperties(updateCmd,domain);
        domain.setMenuKind(MenuKind.getMenuKind(updateCmd.getMenuKind()));
        validate(domain);
        systemMenuGateway.updateSystemMenu(domain);
        return Response.buildSuccess();
    }

    private void validate(SystemMenu validMenu){
        if(validMenu.getIsView()){
            if (validMenu.getIsBaseNode()){
                Assert.notNull(validMenu.getIcon(),"图标不能为空!");
                Assert.notNull(validMenu.getRedirect(),"重定向位置不能为空!");
            }
            Assert.notNull(validMenu.getPath(),"路径不能为空!");
            Assert.notNull(validMenu.getComponent(),"组件不能为空!");
        } else {
            Assert.notNull(validMenu.getApiPath(),"请求路由不能为空!");
        }
        if(!validMenu.getIsBaseNode()){
            Assert.notNull(validMenu.getUpperNode(),"父节点不能为空!");
        }
    }

}
