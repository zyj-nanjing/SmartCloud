package www.bwsensing.com.system.web;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.api.DeptTemplateService;
import www.bwsensing.com.system.api.StructureTemplateService;
import www.bwsensing.com.system.dto.clientobject.DeptTemplateCO;
import www.bwsensing.com.system.dto.command.DeptTemplateSaveCmd;
import www.bwsensing.com.system.dto.command.DeptTemplateUpdateCmd;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.system.dto.clientobject.StructureTemplateCO;
import www.bwsensing.com.system.dto.command.StructureTemplateSaveCmd;
import www.bwsensing.com.system.dto.command.StructureTemplateUpdateCmd;
import www.bwsensing.com.project.dto.command.query.DeptTemplatePageQuery;
import www.bwsensing.com.system.dto.command.query.StructureTemplateSortQuery;
import www.bwsensing.com.system.dto.command.query.StructureTemplateSortPageQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "组织结构模板管理")
@RequestMapping("/api/v1.0/structure/template")
@RestController
public class StructureTemplateController {

    @Autowired
    private StructureTemplateService structureTemplateService;

    @Autowired
    private DeptTemplateService deptTemplateService;

    @ApiOperation("新增组织结构模板")
    @PostMapping("/save")
    public Response addStructureTemplate(@Valid @RequestBody StructureTemplateSaveCmd saveCmd){
        return structureTemplateService.addStructureTemplate(saveCmd);
    }


    @ApiOperation("修改组织结构模板")
    @PostMapping("/update")
    public Response updateStructureTemplate(@Valid @RequestBody StructureTemplateUpdateCmd updateCmd){
        return structureTemplateService.updateStructureTemplate(updateCmd);
    }

    @ApiOperation("分页查询组织结构模板")
    @PostMapping("/page/query")
    public PageResponse<StructureTemplateCO> queryStructureTemplateBySort(@RequestBody StructureTemplateSortPageQuery pageQuery){
        return structureTemplateService.pageQueryStructureTemplateBySort(pageQuery);
    }

    @ApiOperation("查询组织结构模板(用于SELECT)")
    @PostMapping("/query")
    public MultiResponse<StructureTemplateCO> queryStructureTemplate(@RequestBody StructureTemplateSortQuery templateSortQuery){
        return structureTemplateService.queryStructureTemplateBySort(templateSortQuery);
    }

    @ApiOperation("根据ID查询组织结构详情")
    @GetMapping("/query/{id}")
    public SingleResponse<StructureTemplateCO> pathQueryStructureTemplate(@PathVariable Integer id){
        return structureTemplateService.getStructureTemplateById(id);
    }

    @ApiOperation("查询部门模板")
    @PostMapping("/dept/query")
    public MultiResponse<DeptTemplateCO> pageQueryDeptTemplateBySort(@Valid@RequestBody DeptTemplatePageQuery pageQuery){
        return deptTemplateService.pageQueryDeptTemplateBySort(pageQuery);
    }

    @ApiOperation("根据ID查部门模板")
    @GetMapping("/dept/query/{id}")
    public SingleResponse<DeptTemplateCO> pathQueryDeptTemplate(@PathVariable Integer id){
        return deptTemplateService.pathQueryDeptTemplate(id);
    }


    @ApiOperation("新增部门模板")
    @PostMapping("/dept/add")
    public Response addDepartmentTemplate(@Valid@RequestBody DeptTemplateSaveCmd saveCmd){
        return deptTemplateService.addDepartmentTemplate(saveCmd);
    }

    @ApiOperation("修改部门模板")
    @PostMapping("/dept/update")
    public Response updateDepartmentTemplate(@Valid@RequestBody DeptTemplateUpdateCmd updateCmd){
        return deptTemplateService.updateDepartmentTemplate(updateCmd);
    }
}
