package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.domain.device.model.ComputationFunctionCode;
import www.bwsensing.com.domain.device.model.ComputationHandleKind;
import www.bwsensing.com.domain.device.model.DeviceComputation;
import www.bwsensing.com.domain.device.model.data.model.ComputationKind;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DeviceComputationDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class DeviceComputationConvertor {
    private static final BeanCopier DOMAIN_OBJECT_COPIER = BeanCopier.create(DeviceComputationDO.class, DeviceComputation.class,false);
    private static final BeanCopier  DATA_OBJECT_COPIER= BeanCopier.create(DeviceComputation.class, DeviceComputationDO.class,false);

    public static DeviceComputation toDomain(DeviceComputationDO dataObject){
        DeviceComputation domainObject = new DeviceComputation();
        DOMAIN_OBJECT_COPIER.copy(dataObject,domainObject,null);
        if (null != dataObject.getComputationKind() ){
            domainObject.setComputationKind(ComputationKind.getComputationKind(dataObject.getComputationKind()));
        }
        if (null != dataObject.getFunctionCode() ){
            domainObject.setFunctionCode(ComputationFunctionCode.getComputationFunctionCode(dataObject.getFunctionCode()));
        }
        if (null != dataObject.getHandleKind() ){
            domainObject.setHandleKind(ComputationHandleKind.getComputationHandleKind(dataObject.getHandleKind()));
        }
        return domainObject;
    }

    public static List<DeviceComputation> toDomainArray(List<DeviceComputationDO> dataCollection)
    {
        if (null != dataCollection){
            return dataCollection.stream().map(DeviceComputationConvertor::toDomain).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }

    public static DeviceComputationDO toDataObject(DeviceComputation domainObject){
        DeviceComputationDO dataObject = new DeviceComputationDO();
        DATA_OBJECT_COPIER.copy(domainObject,dataObject,null);
        if (null != domainObject.getComputationKind() ){
            dataObject.setComputationKind(domainObject.getComputationKind().getType());
        }
        if (null != domainObject.getFunctionCode() ){
            dataObject.setFunctionCode(domainObject.getFunctionCode().getValue());
        }
        if (null != domainObject.getHandleKind() ){
            dataObject.setHandleKind(domainObject.getHandleKind().getValue());
        }
        return dataObject;
    }
}
