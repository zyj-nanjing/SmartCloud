package www.bwsensing.com.convertor;

import org.springframework.beans.BeanUtils;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemRoleDO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class SystemRoleCoConvertor {
    public static SystemRoleCO toClientObject(SystemRoleDO roleDo){
        SystemRoleCO clientObject = new SystemRoleCO();
        BeanUtils.copyProperties(roleDo,clientObject);
        return clientObject;
    }
    public static List<SystemRoleCO> toClientObjectArray(List<SystemRoleDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SystemRoleCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }
}
