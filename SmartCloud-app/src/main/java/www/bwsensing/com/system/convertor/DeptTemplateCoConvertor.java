package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.system.dto.clientobject.DeptTemplateCO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SysDeptTemplateDO;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author macos-zyj
 */
public class DeptTemplateCoConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SysDeptTemplateDO.class, DeptTemplateCO.class,false);
    public static List<DeptTemplateCO> initDeptTree(List<SysDeptTemplateDO> dataCollection){
        Map<Integer,List<DeptTemplateCO>> deptMap = new LinkedHashMap<>(8);
        dataCollection.forEach(dept -> {
            deptMap.computeIfAbsent(dept.getParentId(), k -> new ArrayList<>());
            deptMap.get(dept.getParentId()).add(toDeptCo(dept));
        });
        return getCurrentDeptAndChild(null,deptMap);
    }

    public  static DeptTemplateCO toDeptCo(SysDeptTemplateDO dataObject){
        DeptTemplateCO clientObject = new DeptTemplateCO();
        DATA_COPIER.copy(dataObject, clientObject,null);
        clientObject.setChildren(new ArrayList<>());
        return clientObject;
    }

    private static List<DeptTemplateCO> getCurrentDeptAndChild(Integer keyLeave,Map<Integer,List<DeptTemplateCO>> dataMap ){
        if(null == dataMap.get(keyLeave) ){
            return new ArrayList<>();
        }
        List<DeptTemplateCO> fatherNodes = new ArrayList<>(dataMap.get(keyLeave));
        for(DeptTemplateCO currentDept : fatherNodes){
            currentDept.setChildren((getCurrentDeptAndChild(currentDept.getId(),dataMap)));
        }
        return  fatherNodes;
    }
}
