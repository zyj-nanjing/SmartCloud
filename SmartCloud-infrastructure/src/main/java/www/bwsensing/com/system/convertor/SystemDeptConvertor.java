package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.organization.SystemDept;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemDeptDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
public class SystemDeptConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SystemDept.class, SystemDeptDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(SystemDeptDO.class,SystemDept.class,false);

    public static SystemDept toDomain(SystemDeptDO dataObject){
        SystemDept domain = new SystemDept();
        DOMAIN_COPIER.copy(dataObject,domain,null);
        return domain;
    }

    public static SystemDeptDO toDataObject(SystemDept domain){
        SystemDeptDO dataObject = new SystemDeptDO();
        DATA_COPIER.copy(domain,dataObject,null);
        return dataObject;
    }

    public static List<SystemDept> toDomainCollection(List<SystemDeptDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(SystemDeptConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
