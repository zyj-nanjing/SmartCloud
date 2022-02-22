package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.monitor.dto.clientobject.AlertTemplateCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertTemplateDO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * 转换器
 * @author macos-zyj
 */
public class AlertTemplateCoConvertor {
    private static final BeanCopier TEMPLATE_COPIER = BeanCopier.create(AlertTemplateDO.class, AlertTemplateCO.class,false);

    public  static AlertTemplateCO toClientObject(AlertTemplateDO dataObject){
        AlertTemplateCO clientObject = new AlertTemplateCO();
        TEMPLATE_COPIER.copy(dataObject,clientObject,null);
        clientObject.setAlertParams(AlertParamCoConvertor.toClientCollection(dataObject.getAlertParams()));
        return clientObject;
    }

    public static List<AlertTemplateCO> toClientObjectList(List<AlertTemplateDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertTemplateCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
