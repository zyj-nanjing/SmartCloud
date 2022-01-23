package www.bwsensing.com.convertor;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.ViewMenuDO;
/**
 * @author macos-zyj
 */
public class SystemMenuCoConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(ViewMenuDO.class, SystemMenuCO.class,false);
    public static List<SystemMenuCO> initMenuTree(List<ViewMenuDO> dataCollection){
        Map<Integer,List<SystemMenuCO>> menuMap = new LinkedHashMap<>(8);
        dataCollection.forEach(view -> {
            menuMap.computeIfAbsent(view.getUpperNode(), k -> new ArrayList<>());
            menuMap.get(view.getUpperNode()).add(toViewMenuCo(view));
        });
        return getCurrentViewAndChild(null,menuMap);
    }

    public  static SystemMenuCO toViewMenuCo(ViewMenuDO dataObject){
        SystemMenuCO treeMenu = new SystemMenuCO();
        DATA_COPIER.copy(dataObject, treeMenu,null);
        treeMenu.setChildren(new ArrayList<>());
        return treeMenu;
    }

    private static List<SystemMenuCO> getCurrentViewAndChild(Integer keyLeave,Map<Integer,List<SystemMenuCO>> dataMap ){
        if(null == dataMap.get(keyLeave) ){
            return new ArrayList<>();
        }
        List<SystemMenuCO> fatherNodes = new ArrayList<>(dataMap.get(keyLeave));
        for(SystemMenuCO currentView : fatherNodes){
            currentView.setChildren((getCurrentViewAndChild(currentView.getId(),dataMap)));
        }
        return  fatherNodes;
    }
}
