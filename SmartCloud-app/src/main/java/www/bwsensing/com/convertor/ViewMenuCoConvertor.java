package www.bwsensing.com.convertor;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.menu.SystemMenu;
import www.bwsensing.com.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.dto.clientobject.ViewMenuTreeCO;
import www.bwsensing.com.dto.clientobject.ViewMetaCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
public class ViewMenuCoConvertor {
    public static List<ViewMenuTreeCO> initViewMenuTree(List<ViewMenuDO> dataCollection){
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

    private  static ViewMenuTreeCO toViewMenuCo(ViewMenuDO dataObject){
        ViewMenuTreeCO treeMenu = new ViewMenuTreeCO();
        BeanUtils.copyProperties(dataObject,treeMenu);
        treeMenu.setMeta(new ViewMetaCO(dataObject.getTitle(), dataObject.getIcon()));
        treeMenu.setChildren(new ArrayList<>());
        return treeMenu;
    }
}
