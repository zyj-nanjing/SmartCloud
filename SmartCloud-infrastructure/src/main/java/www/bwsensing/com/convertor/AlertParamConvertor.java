package www.bwsensing.com.convertor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.alert.AlertParam;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertParamDO;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class AlertParamConvertor {
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(AlertParamDO.class, AlertParam.class,false);
    private static final BeanCopier  DATA_OBJECT_COPIER= BeanCopier.create(AlertParam.class, AlertParamDO.class,false);

    public static AlertParam toDomainObject(AlertParamDO template){
        AlertParam domainObject = new AlertParam();
        DOMAIN_OBJECT_COPIER.copy(template,domainObject,null);
        domainObject.setFormulas(Arrays.asList(template.getFormulas().split("#")));
        return domainObject;
    }



    public static AlertParamDO toDateObject(AlertParam template){
        AlertParamDO dataObject = new AlertParamDO();
        DATA_OBJECT_COPIER.copy(template,dataObject,null);
        dataObject.setFormulas(String.join("#", template.getFormulas()));
        return dataObject;
    }

    public static List<AlertParam> toDomainCollection(List<AlertParamDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertParamConvertor::toDomainObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
