package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.FacilityReceiveCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorReceiveDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author macos-zyj
 */
public class FacilityReceiveCoConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(MonitorReceiveDO.class, FacilityReceiveCO.class,false);

    public  static FacilityReceiveCO toClientObject(MonitorReceiveDO dataObject){
        FacilityReceiveCO clientObject = new FacilityReceiveCO();
        DATA_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<FacilityReceiveCO> toClientObjectList(List<MonitorReceiveDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(FacilityReceiveCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
