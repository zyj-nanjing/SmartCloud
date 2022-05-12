package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.DeviceComputationCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class DeviceComputationCoConvertor {
    private static final BeanCopier CLIENT_OBJECT_COPIER = BeanCopier.create(DeviceComputationDO.class, DeviceComputationCO.class,false);


    public static List<DeviceComputationCO> toClientCollections(List<DeviceComputationDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    DeviceComputationCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }

    public static DeviceComputationCO toClientObject(DeviceComputationDO dataObject){
        DeviceComputationCO clientObject = new DeviceComputationCO();
        CLIENT_OBJECT_COPIER.copy(dataObject, clientObject,null);
        return clientObject;
    }
}
