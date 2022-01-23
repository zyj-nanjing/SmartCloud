package www.bwsensing.com.gatewayimpl;

import com.alibaba.cola.exception.BizException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.convertor.OperateGroupConvertor;
import www.bwsensing.com.domain.gateway.OperateGroupGateway;
import www.bwsensing.com.domain.system.user.OperateGroup;
import www.bwsensing.com.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.OperateGroupDO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Component
public class OperateGroupGatewayImpl implements OperateGroupGateway {
    @Resource
    private OperateGroupMapper operateGroupMapper;

    @Override
    public void save(OperateGroup operateGroup) {
        if (operateGroupMapper.selectGroupCode(operateGroup.getGroupCode())==0){
            OperateGroupDO saveGroup = OperateGroupConvertor.toDataObject(operateGroup);
            operateGroupMapper.save(saveGroup);
        } else{
            throw new BizException("GROUP_CODE_EXIST","用户组编码已存在");
        }
    }

    @Override
    public void update(OperateGroup operateGroup) {
        OperateGroupDO updateGroup = operateGroupMapper.selectGroupById(operateGroup.getId());
        BeanUtils.copyProperties(operateGroup,updateGroup);
        operateGroupMapper.update(updateGroup);
    }

}
