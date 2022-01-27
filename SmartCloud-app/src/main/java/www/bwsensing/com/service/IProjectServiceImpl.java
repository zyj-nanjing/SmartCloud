package www.bwsensing.com.service;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.exception.BizException;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.api.ProjectMemberService;
import java.util.concurrent.atomic.AtomicBoolean;
import www.bwsensing.com.command.ProjectMemberStorageCmdExo;
import www.bwsensing.com.command.query.ProjectPositionQueryExo;
import www.bwsensing.com.common.utills.PageHelperUtils;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.convertor.ProjectCoConvertor;
import www.bwsensing.com.convertor.ProjectMemberCoConvertor;
import www.bwsensing.com.convertor.UserDataCoConvertor;
import www.bwsensing.com.domain.device.SensorInfo;
import www.bwsensing.com.domain.gateway.*;
import www.bwsensing.com.domain.monitor.MonitorStructure;
import www.bwsensing.com.domain.monitor.model.MonitorStructureModel;
import www.bwsensing.com.domain.project.MonitorProject;
import www.bwsensing.com.domain.project.ProjectRoleEnum;
import www.bwsensing.com.dto.command.PositionBindCmd;
import www.bwsensing.com.dto.command.ProjectMemberDeleteCmd;
import www.bwsensing.com.dto.command.ProjectMemberStorageCmd;
import www.bwsensing.com.dto.clientobject.*;
import www.bwsensing.com.dto.command.query.BaseQuery;
import www.bwsensing.com.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.gatewayimpl.database.SensorMapper;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.*;
import www.bwsensing.com.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.domain.system.token.TokenData;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.dto.command.ProjectSaveCmd;
import www.bwsensing.com.api.ProjectService;
import www.bwsensing.com.command.ProjectSaveCmdExo;
import org.springframework.stereotype.Component;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
@CatchAndLog
@Component
public class IProjectServiceImpl implements ProjectService {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private ProjectSaveCmdExo saveCmdExo;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private ProjectMemberService projectMemberService;
    @Resource
    private ProjectMemberStorageCmdExo memberStorageCmdExo;
    @Resource
    private SystemUserMapper userMapper;
    @Resource
    private StructureModelGateway structureModelGateway;
    @Resource
    private MonitorStructureModelMapper structureModelMapper;
    @Resource
    private StructureGateway structureGateway;
    @Resource
    private ProjectMonitorGateway projectMonitorGateway;
    @Resource
    private ProjectPositionQueryExo positionQueryExo;
    @Resource
    private SensorGateway sensorGateway;
    @Resource
    private SensorMapper sensorMapper;


    @Override
    public SingleResponse<Integer> saveProject(ProjectSaveCmd saveCmd) {
        return saveCmdExo.execute(saveCmd);
    }

    @Override
    public Response storageProjectMember(ProjectMemberStorageCmd memberStorageCmd) {
        return memberStorageCmdExo.execute(memberStorageCmd);
    }

    @Override
    public MultiResponse<TreeCO> showProjectSensorTree() {
        List<TreeCO> projectTree = new ArrayList<>();
        List<MonitorProject> projects = projectMonitorGateway.selectProjectByPermission();
        projects.forEach(project -> {
            TreeCO baseTree = new TreeCO();
            baseTree.setId(project.getId());
            baseTree.setLable(project.getName());
            List<SensorDO> sensorList = sensorMapper.selectSensorByProjectId(project.getId());
            List<TreeCO> childrenArray = new ArrayList<>();
            sensorList.forEach(node -> {
                TreeCO children = new TreeCO();
                children.setId(node.getId());
                children.setValue(node.getSn());
                children.setLable(node.getName());
                childrenArray.add(children);
            });
            baseTree.setChildren(childrenArray);
            projectTree.add(baseTree);
        });
        return MultiResponse.of(projectTree);
    }

    @Override
    public Response deleteMember(ProjectMemberDeleteCmd deleteCmd) {
        TokenData tokenData = tokenGateway.getTokenInfo();
        String projectRoleCode = projectMemberService.getCurrentProjectAuthCode(deleteCmd.getProjectId());
        if (ProjectRoleEnum.isAllowedMaxRole(projectRoleCode)){
            if(deleteCmd.getMemberId().equals(tokenData.getUserId())){
                throw new BizException("ACTION_NOT_ALLOW","该用户无权删除自身");
            }
            String deleteUserCode = projectMapper.getProjectRoleByUserId(deleteCmd.getProjectId(), deleteCmd.getMemberId());
            if (StringUtils.isEmpty(deleteUserCode)){
                throw new BizException("MEMBER_NOT_FOUND","该成员不存在");
            }
            if (deleteUserCode.equals(ProjectRoleEnum.PROJECT_OWNER.getRoleCode())){
                throw new BizException("ACTION_NOT_ALLOW","该无权删除项目拥有者");
            }
            if (deleteUserCode.equals(ProjectRoleEnum.PROJECT_MANAGER.getRoleCode())){
                throw new BizException("ACTION_NOT_ALLOW","同一级权限无法删除!");
            }
            projectMapper.deleteMember(deleteCmd.getProjectId(),deleteCmd.getMemberId());
            return Response.buildSuccess();
        }
        throw new BizException("ACTION_NOT_ALLOW","该用户无权删除用户");
    }

    @Override
    public Response deleteProject(Integer projectId) {
        String projectRoleCode = projectMemberService.getCurrentProjectAuthCode(projectId);
        if (ProjectRoleEnum.isAllowedMaxRole(projectRoleCode)){
            projectMonitorGateway.deleteProject(projectId);
            return Response.buildSuccess();
        }
        throw new BizException("ACTION_NOT_ALLOW","该用户无权删除");
    }


    @Transactional(rollbackFor= RuntimeException.class)
    @Override
    public Response synchroStructureModel(Integer modelId) {
        MonitorStructure currentStructure = structureGateway.getMonitorStructureById(modelId);
        Assert.notNull(currentStructure,"需要同步的实例化结构物不存在!");
        String structureCode = structureModelMapper.getStructureCodeById(currentStructure.getModelId());
        MonitorStructureModel lastStructureModel = structureModelGateway.getMonitorStructureModelByCode(structureCode);
        Assert.notNull(lastStructureModel,"同步失败,结构物模板不存在请确认配置!");
        List<SensorInfo> currentSensorCollection = sensorGateway.getSensorsByMonitorStructure(modelId);
        currentStructure.updateStructureVersion(lastStructureModel,currentSensorCollection);
        structureGateway.updateStructure(currentStructure);
        sensorGateway.updateSensors(currentSensorCollection);
        return Response.buildSuccess();
    }

    @Override
    public Response bindPosition(PositionBindCmd bindCmd) {
        if (checkPositionExist(bindCmd.getPositionId())<=0){
            SensorDO bindSensor = new SensorDO();
            bindSensor.setId(bindCmd.getSensorId());
            bindSensor.setProjectId(bindCmd.getProjectId());
            bindSensor.setPositionId(bindCmd.getPositionId());
            if (checkPositionExist(bindCmd.getPositionId())<=0) {
                sensorMapper.bindSensorMethod(bindSensor);
            }
        }
        return Response.buildSuccess();
    }

    private Integer checkPositionExist(Integer positionId) {
        return sensorMapper.countByPositionId(positionId);
    }
    @Override
    public MultiResponse<ProjectCO> selectProjectByPermission() {
        List<ProjectCO> projectCoList = new ArrayList<>();
        projectMonitorGateway.selectProjectByPermission().forEach(e -> {
            ProjectCO clientObject = new ProjectCO();
            clientObject.setId(e.getId());
            clientObject.setPicture(e.getPicture());
            clientObject.setName(e.getName());
            projectCoList.add(clientObject);
        });
        return MultiResponse.of(projectCoList);
    }

    @Override
    public MultiResponse<ProjectPositionCO> queryProjectPosition(Integer projectId) {
        Assert.notNull(projectId,"项目编号不能为空!");
        return positionQueryExo.execute(projectId);
    }

    @Override
    public PageResponse<ProjectCO> projectPageQuery(BaseQuery pageQuery) {
        TokenData dataCo = tokenGateway.getTokenInfo();
        PageHelperUtils<BaseQuery, MonitorProjectDO> pageHelper =
                PageHelperUtils.<BaseQuery,MonitorProjectDO>builder()
                        .pageFunction((groupQuery)->projectMapper.selectMonitorProject(dataCo.getUserId())).build();
        PageInfo<MonitorProjectDO> pageInfo= pageHelper.getPageCollections(pageQuery);
        List<ProjectCO> result = ProjectCoConvertor.toClientCollection(pageInfo.getList());
        return PageResponse.of(result, (int)pageInfo.getTotal(),pageInfo.getPageSize(),pageQuery.getPageIndex() );
    }



    @Override
    public MultiResponse<UserInfoCO> queryCurrentGroupUsers(Integer projectId) {
        Assert.notNull(projectId,"项目编号不能为空!");
        TokenData tokenData = tokenGateway.getTokenInfo();
        List<SystemUserDO> userArray = userMapper.selectUserByPidAndGroupId(projectId,tokenData.getGroupId());
        return MultiResponse.of(UserDataCoConvertor.toClientList(userArray));
    }

    @Override
    public SingleResponse<ProjectCO> projectPathQuery(Integer projectId) {
        if(checkIsContain(projectId)){
            MonitorProjectDO projectDataObject = projectMapper.selectMonitorProjectById(projectId);
            ProjectCO clientObject =  ProjectCoConvertor.toClientObject(projectDataObject);
            return SingleResponse.of(clientObject);
        }
        throw new BizException("ROLE_HAVE_NOT_PERMISSION","该项目无访问权限!");
    }

    @Override
    public MultiResponse<ProjectMemberCO> selectProjectMembers(Integer projectId) {
        Assert.notNull(projectId,"项目编号不能为空!");
        TokenData tokenData = tokenGateway.getTokenInfo();
        if(null != projectMapper.getProjectRoleByUserId(projectId,tokenData.getUserId())){
            List<ProjectMemberDO> projectMembers = projectMapper.selectProjectNumbersByPid(projectId);
            List<ProjectMemberCO> clientObjects = ProjectMemberCoConvertor.clientObjectArray(projectMembers);
            return MultiResponse.of(clientObjects);
        }
        throw new BizException("ROLE_HAVE_NOT_PERMISSION","该项目无访问权限!");
    }

    private boolean checkIsContain(Integer projectId){
        AtomicBoolean result = new AtomicBoolean(false);
        TokenData dataCo = tokenGateway.getTokenInfo();
        List<MonitorProjectDO> projects = projectMapper.selectMonitorProject(dataCo.getUserId());
        projects.forEach(project ->{
             if (project.getId().equals(projectId)){
                 result.set(true);
             }
        });
        return result.get();
    }
}
