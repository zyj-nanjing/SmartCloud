package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.alert.AlertRole;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;

import java.util.Arrays;

/**
 * 预警规则转换器
 * @author macos-zyj
 */
public class AlertRoleConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER = BeanCopier.create(AlertRole.class, AlertRoleDO.class,false);
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(AlertRoleDO.class, AlertRole.class,false);


    public static AlertRoleDO toDataObject(AlertRole alertRole){
        AlertRoleDO dataObject = new AlertRoleDO();
        DATA_OBJECT_COPIER.copy(alertRole,dataObject,null);
        dataObject.setFormulas(String.join("#", alertRole.getFormulas()));
        return dataObject;
    }


    public static AlertRole toDomainObject(AlertRoleDO dataObject){
        AlertRole domainObject = new AlertRole();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        domainObject.setFormulas(Arrays.asList(dataObject.getFormulas().split("#")));
        return domainObject;
    }
}
