package www.bwsensing.com.device.service;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import www.bwsensing.com.device.api.AlertGroupService;
import www.bwsensing.com.project.api.ProjectMemberService;
import www.bwsensing.com.device.command.AlertGroupSaveCmdExo;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.device.convertor.AlertGroupCoConvertor;
import www.bwsensing.com.domain.device.model.alert.AlertGroup;
import www.bwsensing.com.domain.device.gateway.AlertGroupGateway;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.project.model.ProjectRoleEnum;
import www.bwsensing.com.device.dto.command.query.AlertGroupQuery;
import www.bwsensing.com.device.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.device.dto.command.AlertGroupUpdateCmd;
import www.bwsensing.com.device.dto.command.NotificationMemberCmd;
import www.bwsensing.com.device.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.device.dto.clientobject.NotificationMemberBindCO;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.project.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.device.gatewayimpl.database.NotificationMemberMapper;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.AlertGroupDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.NotificationMemberDO;
import www.bwsensing.com.project.gatewayimpl.database.dataobject.ProjectMemberDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author macos-zyj
 */
@Component
@CatchAndLog
public class IAlertGroupServiceImpl implements AlertGroupService {
    @Resource
    private AlertGroupSaveCmdExo  groupSaveCmdExo;
    @Resource
    private AlertGroupGateway alertGroupGateway;
    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private AlertGroupMapper alertGroupMapper;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
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
        PageHelperUtils<AlertGroupQuery, AlertGroupDO> pageHelper =
                PageHelperUtils.<AlertGroupQuery,AlertGroupDO>builder()
                        .pageFunction((groupQuery)->alertGroupMapper.selectAlertGroupBySort (initializeQuery(groupQuery))).build();
        PageInfo<AlertGroupDO> page= pageHelper.getPageCollections(pageQuery);
        List<AlertGroupCO> result = AlertGroupCoConvertor.toClientCollection(page.getList());
        return PageResponse.of(result, (int)page.getTotal(),page.getPageSize(),pageQuery.getPageIndex());
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
        ProductDeviceDO productDeviceDO = productDeviceMapper.getProductDetailById(alertGroup.getCurrentSensorId());
        return  productDeviceDO.getProjectId();
    }

    private AlertGroupDO initializeQuery(AlertGroupQuery pageQuery){
        ProductDeviceDO sensorInfo = productDeviceMapper.getProductDetailByUniqueCode(pageQuery.getCurrentDevice());
        Assert.notNull(sensorInfo,"SENSOR_CODE_NOT_TRUE","设备码不存在!");
        AlertGroupDO query = new AlertGroupDO();
        BeanUtils.copyProperties(pageQuery,query);
        query.setOperateGroupId(tokenGateway.getTokenInfo().getGroupId());
        query.setCurrentSensorId(sensorInfo.getId());
        return query;
    }
}
