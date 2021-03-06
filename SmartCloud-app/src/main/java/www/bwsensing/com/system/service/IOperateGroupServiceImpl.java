package www.bwsensing.com.system.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.system.api.OperateGroupService;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.system.convertor.OperateGroupCoConvertor;
import www.bwsensing.com.system.convertor.UserDataCoConvertor;
import www.bwsensing.com.domain.system.gateway.OperateGroupGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.system.model.user.OperateGroup;
import www.bwsensing.com.system.dto.command.OperateGroupSaveCmd;
import www.bwsensing.com.system.dto.command.OperateGroupUpdateCmd;
import www.bwsensing.com.system.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.common.clientobject.TreeCO;
import www.bwsensing.com.system.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.OperateGroupDO;
import www.bwsensing.com.system.gatewayimpl.database.dataobject.SystemUserDO;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Slf4j
@CatchAndLog
@Component
public class IOperateGroupServiceImpl implements OperateGroupService {
    @Resource
    private OperateGroupGateway operateGroupGateway;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private OperateGroupMapper operateGroupMapper;
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public Response saveGroup(@Valid OperateGroupSaveCmd saveCmd) {
        OperateGroup group = new OperateGroup();
        BeanUtils.copyProperties(saveCmd,group);
        operateGroupGateway.save(group);
        log.info("?????????????????????--------> ??????:{},??????:{}",group.getGroupName(),group.getGroupCode());
        return Response.buildSuccess();
    }

    @Override
    public Response updated(OperateGroupUpdateCmd updateCmd) {
        OperateGroup group = new OperateGroup();
        BeanUtils.copyProperties(updateCmd,group);
        operateGroupGateway.update(group);
        log.info("?????????????????????--------> ??????:{},??????:{}",group.getGroupName(),group.getId());
        return Response.buildSuccess();
    }

    @Override
    public Response deleteGroup(Integer groupId) {
        List<SystemUserDO> userDataList = systemUserMapper.selectUserByGroupId(groupId);
        if(userDataList.size() > 0 ){
            throw new BizException("GROUP_HAVE_MEMBER","??????????????????????????????????????????????????????");
        }
        operateGroupMapper.delete(groupId);
        return Response.buildSuccess();
    }

    @Override
    public MultiResponse<OperateGroupCO> showGroupTree() {
        List<OperateGroupDO> groups = operateGroupMapper.selectOperateGroups();
        List<OperateGroupCO> clientObjects = OperateGroupCoConvertor.toClientList(groups);
        return MultiResponse.of(clientObjects);
    }

    @Override
    public MultiResponse<OperateGroupCO> showGroupTreeByRole() {
        TokenData tokenData = tokenGateway.getTokenInfo();
        List<OperateGroupCO> clientObjects = new ArrayList<>();
        if (tokenData.getRole().equals(RoleConstant.ROOT_ADMIN)){
            clientObjects.addAll(showGroupTree().getData());
        } else{
            OperateGroupDO operateGroupDo = operateGroupMapper.selectGroupById(tokenData.getGroupId());
            clientObjects.add(OperateGroupCoConvertor.toClientObject(operateGroupDo));
        }
        setUserListInGroup(clientObjects);
        return MultiResponse.of(clientObjects);
    }

    @Override
    public MultiResponse<TreeCO> showGroupTreeCoByRole() {
        List<OperateGroupCO> groupsList = showGroupTreeByRole().getData();
        List<TreeCO> treeList = new ArrayList<>(groupsList.size());
        groupsList.forEach( group ->{
            TreeCO clientObjects = new TreeCO();
            clientObjects.setId(group.getId());
            clientObjects.setLable(group.getGroupName());
            treeList.add(clientObjects);
        });
        return MultiResponse.of(treeList);
    }

    private void setUserListInGroup(List<OperateGroupCO> clientObjects){
        if (clientObjects .size()>0){
            for (OperateGroupCO clientObject:clientObjects){
                List<SystemUserDO> userDataList = systemUserMapper.selectUserByGroupId(clientObject.getId());
                if(userDataList.size()>0){
                    clientObject.setUserInfoList(UserDataCoConvertor.toClientList(userDataList));
                }
            }
        }
    }
}
