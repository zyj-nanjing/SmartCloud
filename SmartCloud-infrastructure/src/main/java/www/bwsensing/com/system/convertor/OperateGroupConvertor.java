package www.bwsensing.com.system.convertor;

import org.springframework.beans.BeanUtils;
import www.bwsensing.com.domain.system.model.user.OperateGroup;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.OperateGroupDO;

/**
 * @author macos-zyj
 */
public class OperateGroupConvertor {

    public static OperateGroup toDomain(OperateGroupDO operateGroupDo){
        OperateGroup operateGroup = new OperateGroup();
        BeanUtils.copyProperties(operateGroupDo,operateGroup);
        return operateGroup;
    }


    public static OperateGroupDO toDataObject(OperateGroup operateGroup){
        OperateGroupDO operateGroupDo = new OperateGroupDO();
        BeanUtils.copyProperties(operateGroup,operateGroupDo);
        return operateGroupDo;
    }
}
