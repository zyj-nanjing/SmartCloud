package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.industry.IndustryField;
import www.bwsensing.com.gatewayimpl.database.dataobject.IndustryFieldDO;

/**
 * @author macos-zyj
 */
public class IndustryFieldConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER = BeanCopier.create(IndustryField.class, IndustryFieldDO.class,false);

    public static IndustryFieldDO toDataObject(IndustryField domainObject){
        IndustryFieldDO dataObject = new IndustryFieldDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }
}
