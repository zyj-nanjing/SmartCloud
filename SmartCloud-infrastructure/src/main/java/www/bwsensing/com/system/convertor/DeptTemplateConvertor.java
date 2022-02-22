package www.bwsensing.com.system.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.system.model.organization.SystemDeptTemplate;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SysDeptTemplateDO;


/**
 * @author macos-zyj
 */
public class DeptTemplateConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER = BeanCopier.create(SystemDeptTemplate.class, SysDeptTemplateDO.class,false);
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(SysDeptTemplateDO.class,SystemDeptTemplate.class,false);

    public static SysDeptTemplateDO toDataObject(SystemDeptTemplate domainObject){
        SysDeptTemplateDO dataObject = new SysDeptTemplateDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }

    public static SystemDeptTemplate toDomain(SysDeptTemplateDO dataObject){
        SystemDeptTemplate domainObject = new SystemDeptTemplate();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        return domainObject;
    }

    public static List<SystemDeptTemplate> toDomainArray(List<SysDeptTemplateDO> dataList){
        if (null != dataList){
            return dataList.stream().map(DeptTemplateConvertor::toDomain).collect(toList());
        }
        return new ArrayList<>();
    }
}
