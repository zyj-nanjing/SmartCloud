package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.client.SystemClient;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemClientDO;

/**
 * @author macos-zyj
 */
public class SystemClientConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SystemClient.class, SystemClientDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(SystemClientDO.class,SystemClient.class,false);

    public static SystemClient toDomain(SystemClientDO dataObject){
        if ( null != dataObject ){
            SystemClient domain = new SystemClient();
            DOMAIN_COPIER.copy(dataObject,domain,null);
            if ( null  != dataObject.getInnerStructure()){
                domain.setInnerStructure(SystemStructureConvertor.toDomain(dataObject.getInnerStructure()));
            }
            return domain;
        }
        return null;
    }

    public static SystemClientDO toDataObject(SystemClient domain){
        if (null  != domain){
            SystemClientDO dataObject = new SystemClientDO();
            DATA_COPIER.copy(domain,dataObject,null);
            if ( null  != domain.getInnerStructure()){
                dataObject.setInnerStructure(SystemStructureConvertor.toDataObject(domain.getInnerStructure()));
            }
            return dataObject;
        }
        return null;
    }
}
