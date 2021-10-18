package www.bwsensing.com.project.system.service.impl;

import java.util.*;
import com.alibaba.cola.exception.Assert;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.catchlog.CatchAndLog;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.ViewMenuTreeCO;
import www.bwsensing.com.dto.clientobject.ViewMetaCO;
import www.bwsensing.com.gatewayimpl.database.RoleMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.RoleDO;
import www.bwsensing.com.project.system.domain.ViewMenu;
import www.bwsensing.com.project.system.database.ViewMenuMapper;
import www.bwsensing.com.project.system.service.IViewMenuService;


/**
 * @author macos-zyj
 */
@Slf4j
@CatchAndLog
@Component
public class ViewMenuServiceImpl implements IViewMenuService {
    @Resource
    private ViewMenuMapper menuMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Override
    public List<ViewMenuTreeCO> showViewMenuByAuth() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        RoleDO role = roleMapper.getUserRoleByCode(tokenData.getRole());
        Assert.notNull(role,"当前角色不存在!");
        List<ViewMenu> dataCollection = menuMapper.selectViewMenusByRoleId(role.getId());
        return initViewMenuTree(dataCollection);
    }
    private List<ViewMenuTreeCO> initViewMenuTree(List<ViewMenu> dataCollection){
        List<ViewMenuTreeCO> fatherNodes = new ArrayList<>();
        Map<Integer,ViewMenuTreeCO> menuMap = new LinkedHashMap<>(8);
        dataCollection.forEach(view -> {
            if (view.getIsBaseNode()){
                menuMap.put(view.getId(),toViewMenuCo(view));
            }
        });
        dataCollection.forEach(view -> {
            if (!view.getIsBaseNode()){
                ViewMenuTreeCO fatherNode = menuMap.get(view.getUpperNode());
                if(null != fatherNode){
                    fatherNode.getChildren().add(toViewMenuCo(view));
                }
            }
        });
        for(Integer key : menuMap.keySet()){
            fatherNodes.add(menuMap.get(key));
        }
        return fatherNodes;
    }

    private ViewMenuTreeCO toViewMenuCo(ViewMenu dataObject){
        ViewMenuTreeCO treeMenu = new ViewMenuTreeCO();
        BeanUtils.copyProperties(dataObject,treeMenu);
        treeMenu.setMeta(new ViewMetaCO(dataObject.getTitle(), dataObject.getIcon()));
        treeMenu.setChildren(new ArrayList<>());
        return treeMenu;
    }
}
