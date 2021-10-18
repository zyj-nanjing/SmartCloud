package www.bwsensing.com.convertor;

import org.springframework.beans.BeanUtils;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.gatewayimpl.database.dataobject.RoleDO;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;

/**
 * @author macos-zyj
 */
public class SystemRoleCoConvertor {
    public static SystemRoleCO toClientObject(RoleDO roleDo){
        SystemRoleCO clientObject = new SystemRoleCO();
        BeanUtils.copyProperties(roleDo,clientObject);
        return clientObject;
    }
    public static List<SystemRoleCO> toClientObjectArray(List<RoleDO> dataList){
        if (dataList.size()>0){
            return dataList.stream().map(
                    SystemRoleCoConvertor::toClientObject
            ).collect(toList());
        }
        return new ArrayList<>();
    }
}
