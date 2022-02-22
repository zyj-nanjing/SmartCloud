package www.bwsensing.com.project.web;

import com.alibaba.cola.dto.PageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.common.clientobject.TreeCO;
import www.bwsensing.com.project.dto.command.PositionBindCmd;
import www.bwsensing.com.project.dto.command.ProjectMemberDeleteCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.project.api.ProjectService;
import www.bwsensing.com.project.dto.command.ProjectMemberStorageCmd;
import www.bwsensing.com.project.dto.command.ProjectSaveCmd;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.dto.Response;
import www.bwsensing.com.common.command.BaseQuery;
import www.bwsensing.com.project.dto.clientobject.ProjectCO;
import www.bwsensing.com.project.dto.clientobject.ProjectMemberCO;
import www.bwsensing.com.project.dto.clientobject.ProjectPositionCO;
import www.bwsensing.com.system.dto.clientobject.UserInfoCO;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "项目管理")
@RequestMapping("/api/v1.0/project")
@RestController
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @ApiOperation("新增项目")
    @PostMapping("/save")
    public SingleResponse<Integer> saveProject(@Valid @RequestBody  ProjectSaveCmd saveCmd){
        return projectService.saveProject(saveCmd);
    }

    @ApiOperation("根据项目编号删除项目")
    @GetMapping("/delete/{projectId}")
    public Response deleteProject(@PathVariable Integer projectId){
        return projectService.deleteProject(projectId);
    }

    @ApiOperation("项目人员关联")
    @PostMapping("/storage/member")
    public Response addMember(@Valid @RequestBody ProjectMemberStorageCmd memberStorageCmd){
        return projectService.storageProjectMember(memberStorageCmd);
    }

    @ApiOperation("删除项目人员")
    @PostMapping("/member/delete")
    public Response deleteMember(@Valid @RequestBody ProjectMemberDeleteCmd deleteCmd){
        return projectService.deleteMember(deleteCmd);
    }

    @ApiOperation("项目下传感器树结构查询")
    @GetMapping("/monitor/sensor/device/tree")
    public MultiResponse<TreeCO> showProjectSensorTree(){
        return projectService.showProjectSensorTree();
    }

    @ApiOperation("项目查询(非分页)")
    @GetMapping("/query")
    public MultiResponse<ProjectCO> selectedProject(){
        return projectService.selectProjectByPermission();
    }

    @ApiOperation("同步项目下的结构物版本号")
    @GetMapping("/synchro/model/{modelId}")
    public Response synchroStructureModel(@PathVariable Integer modelId){
        return projectService.synchroStructureModel(modelId);
    }

    @ApiOperation("项目查询(分页)")
    @PostMapping("/page/query")
    public PageResponse<ProjectCO> projectPageQuery(@RequestBody BaseQuery pageQuery){
        return projectService.projectPageQuery(pageQuery);
    }

    @ApiOperation("项目详情查询(根据ID)")
    @GetMapping("/query/{projectId}")
    public SingleResponse<ProjectCO> pathProjectQuery(@PathVariable Integer projectId){
        return projectService.projectPathQuery(projectId);
    }

    @ApiOperation("项目下测点查询(根据项目ID)")
    @GetMapping("/query/positions/{projectId}")
    public MultiResponse<ProjectPositionCO> queryProjectPosition(@PathVariable Integer projectId){
        return projectService.queryProjectPosition(projectId);
    }

    @ApiOperation("传感器测点绑定")
    @PostMapping("/bind/position")
    public Response bindPosition(@Valid @RequestBody PositionBindCmd bindCmd){
        return projectService.bindPosition(bindCmd);
    }

    /**
     * 获取同属于当前
     * @return
     */
    @ApiOperation("当前项目下同小组的人员信息查询")
    @GetMapping("/user/query/{projectId}")
    MultiResponse<UserInfoCO> queryCurrentGroupUsers(@PathVariable Integer projectId) {
        return projectService.queryCurrentGroupUsers(projectId);
    }

    @ApiOperation("根据项目编号获取项目成员集合")
    @GetMapping("/member/query/{projectId}")
    MultiResponse<ProjectMemberCO> selectProjectMembers(@PathVariable Integer projectId){
        return projectService.selectProjectMembers(projectId);
    }
}
