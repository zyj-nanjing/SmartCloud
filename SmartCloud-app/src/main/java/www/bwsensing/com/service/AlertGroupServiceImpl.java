package www.bwsensing.com.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.api.IAlertGroupService;
import www.bwsensing.com.api.IProjectMemberService;
import www.bwsensing.com.command.AlertGroupSaveCmdExo;
import www.bwsensing.com.convertor.AlertGroupCoConvertor;
import www.bwsensing.com.domain.device.alert.AlertGroup;
import www.bwsensing.com.domain.gateway.AlertGroupGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.project.ProjectRoleEnum;
import www.bwsensing.com.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.dto.clientobject.NotificationMemberBindCO;
import www.bwsensing.com.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.dto.command.AlertGroupUpdateCmd;
import www.bwsensing.com.dto.command.NotificationMemberCmd;
import www.bwsensing.com.dto.command.query.AlertGroupQuery;
import www.bwsensing.com.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.gatewayimpl.database.NotificationMemberMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.NotificationMemberDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.ProjectMemberDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SensorDO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class AlertGroupServiceImpl implements IAlertGroupService {
    @Resource
    private AlertGroupSaveCmdExo  groupSaveCmdExo;
    @Resource
    private AlertGroupGateway alertGroupGateway;
    @Resource
    private IProjectMemberService projectMemberService;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private SensorMapper sensorMapper;
    @Resource
    private NotificationMemberMapper notificationMemberMapper;

    @Override
    public Response saveAlertGroup(AlertGroupSaveCmd saveCmd) {
        return groupSaveCmdExo.execute(saveCmd);
    }

    @Override
    public Response updateAlertGroup(AlertGroupUpdateCmd updateCmd) {
        AlertGroup alertGroup = new AlertGroup();
        alertGroup.setId(updateCmd.getId());
        alertGroup.setGroupName(updateCmd.getGroupName());
        alertGroupGateway.updateGroup(alertGroup);
        return Response.buildSuccess();
    }

    @Override
    public Response deleteAlertGroup(Integer id) {
        alertGroupGateway.deleteGroup(id);
        return Response.buildSuccess();
    }

    @Override
    public Response saveNotificationMember(NotificationMemberCmd saveCmd) {
        String projectRoleCode = projectMemberService.getCurrentProjectAuthCode(getMonitorProject(saveCmd.getCurrentGroup()));
        if (ProjectRoleEnum.isAllowedMaxRole(projectRoleCode)){
            notificationMemberMapper.deleteNotificationMemberByGroupId(saveCmd.getCurrentGroup());
            saveCmd.getDataList().forEach(userId->{
                if (null != userId){
                    notificationMemberMapper.saveNotificationMember(saveCmd.getCurrentGroup(),userId);
                }
            });
            return Response.buildSuccess();
        }
        throw new BizException("ACTION_NOT_ALLOW","该用户无权绑定");
    }

    @Override
    public PageResponse<AlertGroupCO> alertGroupPageQuery(AlertGroupQuery pageQuery) {
        PageHelper.startPage(pageQuery.getPageIndex(), pageQuery.getPageSize());
        SensorDO sensorInfo = sensorMapper.selectSensorBySn(pageQuery.getCurrentDevice());
        Assert.notNull(sensorInfo,"SENSOR_CODE_NOT_TRUE","设备码不存在!");
        AlertGroupDO query = new AlertGroupDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setOperateGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setCurrentSensorId(sensorInfo.getId());
        List<AlertGroupDO> resultList = alertGroupMapper.selectAlertGroupBySort(query);
        PageInfo<AlertGroupDO> pageInfo = new PageInfo<>(resultList);
        List<AlertGroupCO> result = AlertGroupCoConvertor.toClientCollection(resultList);
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex());
    }

    @Override
    public MultiResponse<NotificationMemberBindCO> queryNotificationMember(Integer groupId) {
        List<ProjectMemberDO> projectMembers = projectMapper.selectProjectNumbersByPid(getMonitorProject(groupId));
        List<NotificationMemberDO> memberList = notificationMemberMapper.getNotificationMemberByGroupId(groupId);
        List<NotificationMemberBindCO> memberBindList = new ArrayList<>(projectMembers.size());
        projectMembers.forEach(member->{
            NotificationMemberBindCO clientObject = new NotificationMemberBindCO();
            clientObject.setUserId(member.getUserId());
            clientObject.setUserName(member.getUserName());
            if ( memberList.stream().anyMatch( mem -> mem.getUserId().equals(member.getUserId()))){
                clientObject.setSelect(true);
            }
            memberBindList.add(clientObject);
        });
        return MultiResponse.of(memberBindList);
    }

    private Integer getMonitorProject(Integer groupId){
        AlertGroupDO alertGroup = alertGroupMapper.getAlertGroupById(groupId);
        Assert.notNull(alertGroup,"预警分组不存在!");
        SensorDO sensorDO = sensorMapper.selectSensorById(alertGroup.getCurrentSensorId());
        return  sensorDO.getProjectId();
    }
}
