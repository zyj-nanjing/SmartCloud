package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.alert.AlertTemplate;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertTemplateDO;

/**
 * @author macos-zyj
 */
public class AlarmTemplateConvertor {
    private static final BeanCopier  DOMAIN_OBJECT_COPIER = BeanCopier.create(AlertTemplateDO.class, AlertTemplate.class,false);
    private static final BeanCopier DATA_OBJECT_COPIER = BeanCopier.create(AlertTemplate.class, AlertTemplateDO.class,false);

    public static AlertTemplate toDomainObject(AlertTemplateDO template){
        AlertTemplate domainObject = new AlertTemplate();
        DOMAIN_OBJECT_COPIER.copy(template,domainObject,null);
        domainObject.setAlertParams(AlertParamConvertor.toDomainCollection(template.getAlertParams()));
        return domainObject;
    }

    public static AlertTemplateDO toDateObject(AlertTemplate template){
        AlertTemplateDO dataObject = new AlertTemplateDO();
        DATA_OBJECT_COPIER.copy(template,dataObject,null);
        return dataObject;
    }
}
