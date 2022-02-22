package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.system.dto.clientobject.SystemDeptCO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemDeptDO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
public class DepartmentCoConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SystemDeptDO.class, SystemDeptCO.class,false);
    public static List<SystemDeptCO> initDeptTree(List<SystemDeptDO> dataCollection){
        Map<Integer,List<SystemDeptCO>> deptMap = new LinkedHashMap<>(8);
        dataCollection.forEach(dept -> {
            deptMap.computeIfAbsent(dept.getParentId(), k -> new ArrayList<>());
            deptMap.get(dept.getParentId()).add(toDeptCo(dept));
        });
        return getCurrentDeptAndChild(null,deptMap);
    }

    public  static SystemDeptCO toDeptCo(SystemDeptDO dataObject){
        SystemDeptCO clientObject = new SystemDeptCO();
        DATA_COPIER.copy(dataObject, clientObject,null);
        clientObject.setChildren(new ArrayList<>());
        return clientObject;
    }

    private static List<SystemDeptCO> getCurrentDeptAndChild(Integer keyLeave,Map<Integer,List<SystemDeptCO>> dataMap ){
        if(null == dataMap.get(keyLeave) ){
            return new ArrayList<>();
        }
        List<SystemDeptCO> fatherNodes = new ArrayList<>(dataMap.get(keyLeave));
        for(SystemDeptCO currentDept : fatherNodes){
            currentDept.setChildren((getCurrentDeptAndChild(currentDept.getId(),dataMap)));
        }
        return  fatherNodes;
    }
}
