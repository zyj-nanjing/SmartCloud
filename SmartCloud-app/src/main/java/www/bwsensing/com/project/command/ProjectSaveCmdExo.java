package www.bwsensing.com.project.command;

import com.alibaba.cola.dto.SingleResponse;
import www.bwsensing.com.domain.system.gateway.TokenGateway;
import www.bwsensing.com.domain.monitor.model.MonitorStructure;
import www.bwsensing.com.domain.project.model.ProjectMember;
import www.bwsensing.com.monitor.dto.command.ModelGenerateCmd;
import www.bwsensing.com.monitor.gatewayimpl.database.dataobject.MonitorStructureModelDO;
import www.bwsensing.com.monitor.gatewayimpl.database.MonitorStructureModelMapper;
import www.bwsensing.com.domain.monitor.model.model.MonitorStructureModel;
import www.bwsensing.com.monitor.convertor.StructureModelConvertor;
import www.bwsensing.com.domain.project.gateway.ProjectMonitorGateway;
import www.bwsensing.com.domain.system.gateway.SystemUserGateway;
import www.bwsensing.com.domain.project.model.MonitorProject;
import www.bwsensing.com.domain.system.model.user.SystemUser;
import www.bwsensing.com.domain.system.model.token.TokenData;
import org.springframework.stereotype.Component;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.project.dto.command.ProjectSaveCmd;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * @author macos-zyj
 */
@Component
public class ProjectSaveCmdExo {
    @Resource
    private TokenGateway tokenGateway;
    @Resource
    private ProjectMonitorGateway projectMonitorGateway;
    @Resource
    private MonitorStructureModelMapper structureModelMapper;
    @Resource
    private SystemUserGateway systemUserGateway;

    public SingleResponse<Integer> execute(ProjectSaveCmd saveCmd){
        TokenData tokenData = tokenGateway.getTokenInfo();
        SystemUser systemUser = systemUserGateway.loadUserByAccountName(tokenData.getAccountName());
        MonitorProject project = initProject(systemUser,saveCmd);
        project.setInnerMembers(initOwner(tokenData));
        Integer result = projectMonitorGateway.saveProject(project);
        return SingleResponse.of(result);
    }

    private MonitorProject initProject(SystemUser systemUser,ProjectSaveCmd saveCmd){
        MonitorProject project = new MonitorProject();
        project.setName(saveCmd.getName());
        project.setOwner(systemUser);
        project.setPicture(saveCmd.getPicture());
        project.create();
        project.setStructureList(getGenerateStructures(saveCmd.getStructureList(),systemUser.getAccountName()));
        return project;
    }

    private List<ProjectMember> initOwner(TokenData tokenData){
        List<ProjectMember>  memberArray = new ArrayList<>();
        ProjectMember projectMember = new ProjectMember();
        projectMember.createOwner(tokenData.getUserId(),tokenData.getAccountName());
        memberArray.add(projectMember);
        return memberArray;
    }

    private List<MonitorStructure> getGenerateStructures(List<ModelGenerateCmd> structureList,String creator){
        List<MonitorStructure> monitorStructureModels = new ArrayList(structureList.size());
        if(structureList.size() >0){
            structureList.forEach(generateCmd->{
                MonitorStructureModelDO modelDo = structureModelMapper.selectStructureModelById(generateCmd.getModelId());
                if(null !=modelDo){
                    MonitorStructureModel selectedModel = StructureModelConvertor.toDomain(modelDo);
                    monitorStructureModels.add(selectedModel.initMonitorStructure(generateCmd.getGenerateName(),creator,selectedModel.getId()));
                }
           });
            if(monitorStructureModels.size() >0){
                return monitorStructureModels;
            }
        }
        throw new BizException("STRUCTURE_CHOICE_ERROR","结构物选择错误请确定结构物设置!");
    }
}
