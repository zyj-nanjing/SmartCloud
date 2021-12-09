package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IOperateGroupService;
import www.bwsensing.com.common.constant.RoleConstant;
import www.bwsensing.com.convertor.OperateGroupCoConvertor;
import www.bwsensing.com.convertor.UserDataCoConvertor;
import www.bwsensing.com.domain.gateway.OperateGroupGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.system.OperateGroup;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.dto.clientobject.OperateGroupCO;
import www.bwsensing.com.dto.clientobject.TreeCO;
import www.bwsensing.com.dto.command.OperateGroupSaveCmd;
import www.bwsensing.com.dto.command.OperateGroupUpdateCmd;
import www.bwsensing.com.gatewayimpl.database.OperateGroupMapper;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.OperateGroupDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;

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
public class OperateGroupServiceImpl implements IOperateGroupService {
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
        log.info("项目组保存成功--------> 名称:{},编号:{}",group.getGroupName(),group.getGroupCode());
        return Response.buildSuccess();
    }

    @Override
    public Response updated(OperateGroupUpdateCmd updateCmd) {
        OperateGroup group = new OperateGroup();
        BeanUtils.copyProperties(updateCmd,group);
        operateGroupGateway.update(group);
        log.info("项目组修改成功--------> 名称:{},编号:{}",group.getGroupName(),group.getId());
        return Response.buildSuccess();
    }

    @Override
    public Response deleteGroup(Integer groupId) {
        List<SystemUserDO> userDataList = systemUserMapper.selectUserByGroupId(groupId);
        if(userDataList.size() > 0 ){
            throw new BizException("GROUP_HAVE_MEMBER","该小组小有成员请全部删除后再进行删除");
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
