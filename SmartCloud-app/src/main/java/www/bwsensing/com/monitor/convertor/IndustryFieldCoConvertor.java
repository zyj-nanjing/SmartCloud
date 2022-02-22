package www.bwsensing.com.monitor.convertor;

import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.monitor.dto.clientobject.IndustryFieldCO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.IndustryFieldDO;

/**
 * @author macos-zyj
 */
public class IndustryFieldCoConvertor {
    private static final BeanCopier DATA_COPIER = BeanCopier.create(IndustryFieldDO.class, IndustryFieldCO.class,false);

    public  static IndustryFieldCO toClientObject(IndustryFieldDO dataObject){
        IndustryFieldCO clientObject = new IndustryFieldCO();
        DATA_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<IndustryFieldCO> toClientObjectList(List<IndustryFieldDO> dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(IndustryFieldCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
