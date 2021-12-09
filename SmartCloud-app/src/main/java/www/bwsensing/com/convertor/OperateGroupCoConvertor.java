package www.bwsensing.com.convertor;

import org.springframework.cglib.beans.BeanCopier;
import www.bwsensing.com.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.OperateGroupDO;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class OperateGroupCoConvertor {
    private static final BeanCopier CLIENT_COPIER = BeanCopier.create(OperateGroupDO.class, OperateGroupCO.class,false);
    public static OperateGroupCO toClientObject(OperateGroupDO dataObject){
        OperateGroupCO clientObject = new OperateGroupCO();
        CLIENT_COPIER.copy(dataObject,clientObject,null);
        return clientObject;
    }

    public static List<OperateGroupCO> toClientList(List<OperateGroupDO>dataCollection){
        if (null != dataCollection){
            return dataCollection.stream().map(OperateGroupCoConvertor::toClientObject).collect(toList());
        }else{
            return new ArrayList<>();
        }
    }
}
