package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.client.SystemClient;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemClientDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;

/**
 * @author macos-zyj
 */
public class SystemClientConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SystemClient.class, SystemClientDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(SystemClientDO.class,SystemClient.class,false);

    public static SystemClient toDomain(SystemRoleDO dataObject){
        SystemClient domain = new SystemClient();
        DOMAIN_COPIER.copy(dataObject,domain,null);
        return domain;
    }

    public static SystemClientDO toDataObject(SystemClient domain){
        SystemClientDO dataObject = new SystemClientDO();
        DATA_COPIER.copy(domain,dataObject,null);
        return dataObject;
    }
}
