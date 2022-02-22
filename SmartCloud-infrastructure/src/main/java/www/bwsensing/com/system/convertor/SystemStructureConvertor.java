package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.organization.StructureTypeEnum;
import www.bwsensing.com.domain.system.model.organization.SystemStructure;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemStructureDO;

/**
 * @author macos-zyj
 */
public class SystemStructureConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(SystemStructure.class, SystemStructureDO.class,false);
    private static final BeanCopier DOMAIN_COPIER = BeanCopier.create(SystemStructureDO.class,SystemStructure.class,false);

    public static SystemStructure toDomain(SystemStructureDO dataObject){
        SystemStructure domain = new SystemStructure();
        DOMAIN_COPIER.copy(dataObject,domain,null);
        domain.setStructureType(StructureTypeEnum.getStructureType(dataObject.getTypeId()));
        return domain;
    }

    public static SystemStructureDO toDataObject(SystemStructure domain){
        SystemStructureDO dataObject = new SystemStructureDO();
        DATA_COPIER.copy(domain,dataObject,null);
        dataObject.setTypeId(domain.getStructureType().getTypeId());
        return dataObject;
    }
}
