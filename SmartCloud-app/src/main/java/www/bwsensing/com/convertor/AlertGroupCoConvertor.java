package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertGroupDO;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class AlertGroupCoConvertor {
    private static final BeanCopier GROUP_CLIENT_COPIER = BeanCopier.create(AlertGroupDO.class, AlertGroupCO.class,false);

    public static AlertGroupCO toClientObject(AlertGroupDO dataObject){
        AlertGroupCO clientObject = new AlertGroupCO();
        GROUP_CLIENT_COPIER.copy(dataObject,clientObject,null);
        return  clientObject;
    }

    public static List<AlertGroupCO> toClientCollection(List<AlertGroupDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(AlertGroupCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
