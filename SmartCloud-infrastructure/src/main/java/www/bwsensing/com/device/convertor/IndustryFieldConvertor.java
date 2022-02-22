package www.bwsensing.com.device.convertor;

import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;
import www.bwsensing.com.domain.monitor.model.industry.IndustryField;
import org.springframework.cglib.beans.BeanCopier;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class IndustryFieldConvertor {
    private static final BeanCopier DATA_OBJECT_COPIER = BeanCopier.create(IndustryField.class, IndustryFieldDO.class,false);
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(IndustryFieldDO.class,IndustryField.class,false);
    public static IndustryFieldDO toDataObject(IndustryField domainObject){
        IndustryFieldDO dataObject = new IndustryFieldDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        return dataObject;
    }

    public static IndustryField toDomain(IndustryFieldDO dataObject){
        IndustryField domainObject = new IndustryField();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        return domainObject;
    }

    public static List<IndustryField> toDomainArray(List<IndustryFieldDO> dataList){
        if (null != dataList){
            return dataList.stream().map(IndustryFieldConvertor::toDomain).collect(toList());
        }
        return new ArrayList<>();
    }
}
