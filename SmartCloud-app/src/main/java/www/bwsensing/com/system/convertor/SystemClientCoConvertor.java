package www.bwsensing.com.system.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.system.dto.clientobject.SystemClientCO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemClientDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class SystemClientCoConvertor {
    private static final BeanCopier CLIENT_OBJECT_COPIER = BeanCopier.create(SystemClientDO.class, SystemClientCO.class,false);

    public static List<SystemClientCO> toClientObjectArray(List<SystemClientDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SystemClientCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static SystemClientCO toClientObject(SystemClientDO sensorModel){
        SystemClientCO clientObject = new SystemClientCO();
        CLIENT_OBJECT_COPIER.copy(sensorModel,clientObject,null);
        return clientObject;
    }
}
