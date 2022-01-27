package www.bwsensing.com.convertor;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.system.menu.SystemMenu;
import www.bwsensing.com.domain.system.organization.SystemDept;
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
    private static final String API_KEY = "A";
    public static List<ViewMenuTreeCO> initViewMenuTree(List<ViewMenuDO> dataCollection,Boolean allowApi){
        Map<Integer,List<ViewMenuTreeCO>> dataMap = new LinkedHashMap<>();
        for(ViewMenuDO current : dataCollection){
            if(StringUtils.isNotEmpty(current.getMenuKind())){
                if(!API_KEY.equals(current.getMenuKind())||allowApi){
                    dataMap.computeIfAbsent(current.getUpperNode(), k -> new ArrayList<>());
                    dataMap.get(current.getUpperNode()).add(toViewMenuCo(current));
                }
            }
        }
        return  getCurrentMenuAndChild(null,dataMap);
    }

    private  static ViewMenuTreeCO toViewMenuCo(ViewMenuDO dataObject){
        ViewMenuTreeCO treeMenu = new ViewMenuTreeCO();
        BeanUtils.copyProperties(dataObject,treeMenu);
        treeMenu.setMeta(new ViewMetaCO(dataObject.getTitle(), dataObject.getIcon()));
        treeMenu.setChildren(new ArrayList<>());
        return treeMenu;
    }

    private static List<ViewMenuTreeCO> getCurrentMenuAndChild(Integer keyLeave,Map<Integer,List<ViewMenuTreeCO>> dataMap ){
        if(null == dataMap.get(keyLeave) ){
            return new ArrayList<>();
        }
        List<ViewMenuTreeCO> fatherNodes = new ArrayList<>(dataMap.get(keyLeave));
        for(ViewMenuTreeCO current : fatherNodes){
            current.setChildren(getCurrentMenuAndChild(current.getId(),dataMap));
        }
        return  fatherNodes;
    }
}
