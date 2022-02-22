package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.role.DataScopeEnum;
import www.bwsensing.com.domain.system.model.role.UserRole;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemRoleDO;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class SystemRoleConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(UserRole.class, SystemRoleDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(SystemRoleDO.class,UserRole.class,false);

    public static UserRole toDomain(SystemRoleDO dataObject){
        UserRole domain = new UserRole();
        DOMAIN_COPIER.copy(dataObject,domain,null);
        domain.setDataScope(DataScopeEnum.getDataScope(dataObject.getDataScope()));
        return domain;
    }

    public static List<UserRole> toDomainCollection(List<SystemRoleDO> dataCollection){
        return dataCollection.stream().map(SystemRoleConvertor::toDomain).collect(toList());
    }

    public static SystemRoleDO toDataObject(UserRole domain){
        SystemRoleDO dataObject = new SystemRoleDO();
        DATA_COPIER.copy(domain,dataObject,null);
        dataObject.setDataScope(domain.getDataScope().getScopeId());
        return dataObject;
    }
}
