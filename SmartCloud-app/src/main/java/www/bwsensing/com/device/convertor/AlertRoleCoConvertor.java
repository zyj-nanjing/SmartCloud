package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertRoleDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author macos-zyj
 */
public class AlertRoleCoConvertor {
    private static final BeanCopier ROLE_CLIENT_COPIER = BeanCopier.create(AlertRoleDO.class, AlertRoleCO.class,false);
    public static AlertRoleCO toClientObject(AlertRoleDO dataObject){
        AlertRoleCO clientObject = new AlertRoleCO();
        ROLE_CLIENT_COPIER.copy(dataObject,clientObject,null);
        clientObject.setFormulas(Arrays.asList(dataObject.getFormulas().split("#")));
        return  clientObject;
    }

    public static List<AlertRoleCO> toClientCollection(List<AlertRoleDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertRoleCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
