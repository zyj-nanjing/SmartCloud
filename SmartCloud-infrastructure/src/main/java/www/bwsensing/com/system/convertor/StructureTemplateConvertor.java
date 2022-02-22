package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.organization.StructureTemplate;
import www.bwsensing.com.domain.system.model.organization.SystemDeptTemplate;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SysStructureTemplateDO;

import java.util.List;

/**
 * @author macos-zyj
 */
public class StructureTemplateConvertor {
    private static final BeanCopier STRUCTURE_COPIER = BeanCopier.create(StructureTemplate.class, SysStructureTemplateDO.class,false);
    private static final BeanCopier STRUCTURE_DOMAIN_COPIER = BeanCopier.create(SysStructureTemplateDO.class,StructureTemplate.class,false);

    public static SysStructureTemplateDO toDataObject(StructureTemplate domainObject){
        SysStructureTemplateDO dataObject = new SysStructureTemplateDO();
        STRUCTURE_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }

    public static StructureTemplate toDomain(SysStructureTemplateDO dataObject){
        List<SystemDeptTemplate> deptTemplates = null;
        if (null != dataObject.getDeptTemplates()&& dataObject.getDeptTemplates().size()>0){
            deptTemplates = DeptTemplateConvertor.toDomainArray(dataObject.getDeptTemplates());
            dataObject.getDeptTemplates().clear();
        }
        StructureTemplate domainObject = new StructureTemplate();
        STRUCTURE_DOMAIN_COPIER.copy(dataObject,domainObject,null);
        if( null != deptTemplates){
            domainObject.setDeptTemplates(deptTemplates);
        }
        return domainObject;
    }
}
