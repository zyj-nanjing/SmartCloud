package www.bwsensing.com.command;

import javax.annotation.Resource;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.BizException;
import org.springframework.stereotype.Component;
import www.bwsensing.com.common.utills.StringUtils;
import www.bwsensing.com.domain.gateway.ProjectMonitorGateway;
import www.bwsensing.com.domain.gateway.TokenGateway;
import www.bwsensing.com.domain.project.ProjectMember;
import www.bwsensing.com.domain.project.ProjectRoleEnum;
import www.bwsensing.com.dto.command.ProjectMemberStorageCmd;
import www.bwsensing.com.domain.system.token.TokenData;
import www.bwsensing.com.gatewayimpl.database.MonitorProjectMapper;
import www.bwsensing.com.gatewayimpl.database.SystemUserMapper;
import www.bwsensing.com.gatewayimpl.database.dataobject.MonitorProjectDO;
import www.bwsensing.com.gatewayimpl.database.dataobject.SystemUserDO;

/**
 * @author macos-zyj
 * 1 校验 （1、校验项目是否存在 2、校验该用户是否为项目管理员或 项目拥有者 3、校验用户编号是否存在 且该用户是否已被添加）
 * 2 保存
 */
@Component
public class ProjectMemberStorageCmdExo {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private MonitorProjectMapper projectMapper;
    @Resource
    private SystemUserMapper userMapper;
    @Resource
    private ProjectMonitorGateway projectGateway;

    public Response execute(ProjectMemberStorageCmd saveCmd){
        TokenData tokenData = tokenGateway.getTokenInfo();
        validProjectExist(saveCmd.getProjectId());
        validUserRoleAndId(saveCmd,tokenData);
        updateOrSaveProjectMember(saveCmd,tokenData);
        return  Response.buildSuccess();
    }

    private  void validProjectExist(Integer projectId){
        MonitorProjectDO dataObject = projectMapper.selectMonitorProjectById(projectId);
        if(null == dataObject){
            throw new BizException("PROJECT_NOT_EXIST","项目编号对应的项目不存在请检查项目编号");
        }
    }

    private void validUserRoleAndId(ProjectMemberStorageCmd saveCmd, TokenData tokenData){
        SystemUserDO userDao = userMapper.selectUserById(saveCmd.getMemberId());
        if (null  == userDao){
            throw new BizException("USER_NOT_EXIST","用户编号对应的用户不存在请检查项目编号");
        } else{
            if(saveCmd.getRoleCode().equals(ProjectRoleEnum.PROJECT_OWNER.getRoleCode())){
                throw new BizException("ACTION_NOT_ALLOW","项目保存后无法添加项目拥有者");
            }
            String projectRoleCode = projectMapper.getProjectRoleByUserId(saveCmd.getProjectId(), tokenData.getUserId());
            if(!ProjectRoleEnum.isAllowedMaxRole(projectRoleCode)){
                throw new BizException("ACTION_NOT_ALLOW","该用户无权添加用户");
            }
            if(!tokenData.getGroupId().equals(userDao.getOperateGroupId())){
                throw new BizException("USER_NOT_EXIST","用户编号对应的用户不存在请检查用户编号");
            }
        }

    }
    private void updateOrSaveProjectMember(ProjectMemberStorageCmd saveCmd, TokenData tokenData){
        String projectRoleCode = projectMapper.getProjectRoleByUserId(saveCmd.getProjectId(), saveCmd.getMemberId());
        ProjectMember projectMember = new ProjectMember();
        projectMember.createMember(saveCmd.getProjectId(), saveCmd.getRoleCode());
        projectMember.setUserId(saveCmd.getMemberId());
        if(StringUtils.isNotEmpty(projectRoleCode)){
            projectGateway.updateProjectNumber(projectMember);
        } else{
            projectGateway.addProjectNumber(projectMember);
        }
    }


}
