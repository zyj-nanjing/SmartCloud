package www.bwsensing.com.system.web;

import javax.validation.Valid;

import com.alibaba.cola.dto.MultiResponse;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.ApiOperation;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.api.DepartmentService;
import www.bwsensing.com.system.api.StructureService;
import www.bwsensing.com.system.dto.clientobject.SystemDeptCO;
import www.bwsensing.com.system.dto.clientobject.SystemStructureCO;
import www.bwsensing.com.system.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.system.dto.command.DepartmentUpdateCmd;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.project.dto.command.query.DeptPageQuery;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "组织结构管理")
@RequestMapping("/api/v1.0/structure")
@RestController
public class StructureController {
    @Autowired
    private StructureService structureService;

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("根据ID查询组织结构详情")
    @GetMapping("/query/{id}")
    public SingleResponse<SystemStructureCO> pathQueryStructure(@PathVariable Integer id){
        return structureService.getStructureById(id);
    }

    @ApiOperation("查询部门")
    @PostMapping("/dept/query")
    public MultiResponse<SystemDeptCO> pageQueryDepartmentBySort(@Valid@RequestBody DeptPageQuery pageQuery){
        return departmentService.pageQueryDepartmentBySort(pageQuery);
    }

    @ApiOperation("根据ID查询部门详情")
    @GetMapping("/dept/query/{id}")
    public SingleResponse<SystemDeptCO> pathQueryDept(@PathVariable Integer id){
        return departmentService.getDepartmentById(id);
    }

    @ApiOperation("新增部门")
    @PostMapping("/dept/add")
    public Response addDepartment(@Valid@RequestBody DepartmentSaveCmd saveCmd){
        return departmentService.addDepartment(saveCmd);
    }

    @ApiOperation("修改部门")
    @PostMapping("/dept/update")
    public Response updateDepartment(@Valid@RequestBody DepartmentUpdateCmd updateCmd){
        return departmentService.updateDepartment(updateCmd);
    }
}
