package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IProjectService;
import www.bwsensing.com.dto.clientobject.*;
import www.bwsensing.com.dto.command.PositionBindCmd;
import www.bwsensing.com.dto.command.ProjectMemberDeleteCmd;
import www.bwsensing.com.dto.command.ProjectMemberStorageCmd;
import www.bwsensing.com.dto.command.ProjectSaveCmd;
import www.bwsensing.com.dto.command.query.BaseQuery;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/project")
@RestController
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @PostMapping("/save")
    public SingleResponse<Integer> saveProject(@Valid @RequestBody  ProjectSaveCmd saveCmd){
        return projectService.saveProject(saveCmd);
    }
    @GetMapping("/delete/{projectId}")
    public Response deleteProject(@PathVariable Integer projectId){
        return projectService.deleteProject(projectId);
    }

    @PostMapping("/storage/member")
    public Response addMember(@Valid @RequestBody ProjectMemberStorageCmd memberStorageCmd){
        return projectService.storageProjectMember(memberStorageCmd);
    }

    @PostMapping("/member/delete")
    public Response deleteMember(@Valid @RequestBody ProjectMemberDeleteCmd deleteCmd){
        return projectService.deleteMember(deleteCmd);
    }

    @GetMapping("/monitor/sensor/device/tree")
    public MultiResponse<TreeCO> showProjectSensorTree(){
        return projectService.showProjectSensorTree();
    }

    @GetMapping("/query")
    public MultiResponse<ProjectCO> selectedProject(){
        return projectService.selectProjectByPermission();
    }

    @GetMapping("/synchro/model/{modelId}")
    public Response synchroStructureModel(@PathVariable Integer modelId){
        return projectService.synchroStructureModel(modelId);
    }

    @PostMapping("/page/query")
    public PageResponse<ProjectCO> projectPageQuery(@RequestBody BaseQuery pageQuery){
        return projectService.projectPageQuery(pageQuery);
    }

    @GetMapping("/query/{projectId}")
    public SingleResponse<ProjectCO> pathProjectQuery(@PathVariable Integer projectId){
        return projectService.projectPathQuery(projectId);
    }

    @GetMapping("/query/positions/{projectId}")
    public MultiResponse<ProjectPositionCO> queryProjectPosition(@PathVariable Integer projectId){
        return projectService.queryProjectPosition(projectId);
    }

    @PostMapping("/bind/position")
    public Response bindPosition(@Valid @RequestBody PositionBindCmd bindCmd){
        return projectService.bindPosition(bindCmd);
    }

    /**
     * 获取同属于当前
     * @return
     */
    @GetMapping("/user/query/{projectId}")
    MultiResponse<UserInfoCO> queryCurrentGroupUsers(@PathVariable Integer projectId) {
        return projectService.queryCurrentGroupUsers(projectId);
    }

    @GetMapping("/member/query/{projectId}")
    MultiResponse<ProjectMemberCO> selectProjectMembers(@PathVariable Integer projectId){
        return projectService.selectProjectMembers(projectId);
    }
}
