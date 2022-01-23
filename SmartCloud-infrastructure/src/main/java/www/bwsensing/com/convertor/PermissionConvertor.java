package www.bwsensing.com.convertor;

import java.util.List;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.role.Permission;
import www.bwsensing.com.gatewayimpl.database.dataobject.PermissionDO;


/**
 * @author macos-zyj
 */
public class PermissionConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(Permission.class, PermissionDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(PermissionDO.class,Permission.class,false);

    public static Permission toDomain(PermissionDO dataObject){
        Permission domain = new Permission();
        DOMAIN_COPIER.copy(dataObject,domain,null);
        return domain;
    }

    public static List<Permission> toDomainCollection(List<PermissionDO> dataCollection){
        return dataCollection.stream().map(PermissionConvertor::toDomain).collect(toList());
    }

    public static PermissionDO toDataObject(Permission domain){
        PermissionDO dataObject = new PermissionDO();
        DATA_COPIER.copy(domain,dataObject,null);
        return dataObject;
    }
}
