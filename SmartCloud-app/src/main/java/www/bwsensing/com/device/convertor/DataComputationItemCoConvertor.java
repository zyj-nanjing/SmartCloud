package www.bwsensing.com.device.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.device.dto.clientobject.DataComputationItemCO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.DataComputationItemDO;
import static java.util.stream.Collectors.toList;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
public class DataComputationItemCoConvertor {
    private static final BeanCopier COPIER = BeanCopier.create(DataComputationItemDO.class, DataComputationItemCO.class,false);

    public  static DataComputationItemCO toClientObject(DataComputationItemDO dataObject){
        DataComputationItemCO clientObject = new DataComputationItemCO();
        COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<DataComputationItemCO> toClientObjectList(List<DataComputationItemDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(DataComputationItemCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
