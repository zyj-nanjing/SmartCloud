package www.bwsensing.com.project.gatewayimpl;

import java.util.List;
import javax.annotation.Resource;

import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import www.bwsensing.com.device.gatewayimpl.database.ProductDeviceMapper;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.project.model.MonitorProject;
import www.bwsensing.com.domain.project.model.ProjectMember;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorPositionMapper;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureMapper;
import www.bwsensing.com.project.convertor.ProjectConvertor;
import www.bwsensing.com.domain.monitor.gateway.StructureGateway;
import www.bwsensing.com.domain.project.gateway.ProjectMonitorGateway;
import www.bwsensing.com.domain.system.model.token.TokenData;
import www.bwsensing.com.project.gatewayimpl.database.dataobject.MonitorProjectDO;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureDO;
import www.bwsensing.com.project.gatewayimpl.database.dataobject.ProjectMemberDO;
import www.bwsensing.com.device.gatewayimpl.database.dataobject.ProductDeviceDO;
import www.bwsensing.com.project.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.system.gatewayimpl.database.SystemUserMapper;

/**
 * @author macos-zyj
 */
@Component
public class ProjectMonitorGatewayImpl implements ProjectMonitorGateway {
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private StructureGateway structureGateway;
    @Resource
    private MonitorStructureMapper structureMapper;
    @Resource
    private MonitorPositionMapper positionMapper;
    @Resource
    private ProductDeviceMapper productDeviceMapper;
    @Resource
    private SystemUserMapper userMapper;
    @Resource
    private TokenGateway tokenGateway;

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public Integer saveProject(MonitorProject monitorProject) {
        MonitorProjectDO dataObject = ProjectConvertor.toDataObject(monitorProject);
        projectMapper.save(dataObject);
        ProjectMember projectMember = monitorProject.getInnerMembers().get(0);
        projectMember.setProjectId(dataObject.getId());
        addProjectNumber(projectMember);
        monitorProject.getStructureList().forEach( structure ->{
            structure.setProjectId(dataObject.getId());
            structureGateway.saveStructure(structure);
        });
        return dataObject.getId();
    }

    @Override
    public List<MonitorProject> selectProjectByPermission() {
        TokenData dataCo = tokenGateway.getTokenInfo();
        List<MonitorProjectDO> projects = projectMapper.selectMonitorProject(dataCo.getUserId());
        return ProjectConvertor.toDomainArray(projects);
    }

    @Override
    public void addProjectNumber(ProjectMember member) {
        ProjectMemberDO projectMember = new ProjectMemberDO();
        projectMember.setProjectId(member.getProjectId());
        projectMember.setRoleName(member.getRoleName());
        projectMember.setRoleCode(member.getRoleCode());
        projectMember.setUserId(member.getUserId());
        projectMember.setJoinTime(member.getJoinTime());
        String accountName = userMapper.selectUserById(member.getUserId()).getAccountName();
        projectMember.setAccountName(accountName);
        projectMapper.saveMember(projectMember);
    }

    @Override
    public void deleteProjectNumber(ProjectMember member) {

    }

    @Override
    public void updateProjectNumber(ProjectMember member) {
        ProjectMemberDO projectMember = projectMapper.selectMemberByUserId(member.getProjectId(), member.getUserId());
        if ( null != projectMember){
            projectMember.setRoleCode(member.getRoleCode());
            projectMember.setRoleName(member.getRoleName());
            projectMapper.updateMember(projectMember);
        }
    }

    @Override
    public void updateProject(MonitorProject monitorProject) {

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deleteProject(Integer projectId) {
        List<ProductDeviceDO> sensors = productDeviceMapper.selectSensorByProjectId(projectId);
        if ( null !=sensors&& sensors.size() > 0){
            throw new BizException("PROJECT_CONTAIN_SENSOR","项目中已经包含传感器请先解绑!");
        }
        List<MonitorStructureDO> structureList = structureMapper.selectStructureByProjectId(projectId);
        structureList.forEach(structure->positionMapper.deleteByStructureId(structure.getId()));
        structureMapper.deleteByProjectId(projectId);
        projectMapper.deleteMemberByProjectId(projectId);
        projectMapper.deleteProject(projectId);
    }

}
