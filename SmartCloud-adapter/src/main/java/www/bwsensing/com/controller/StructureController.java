package www.bwsensing.com.controller;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.ApiOperation;
import com.alibaba.cola.dto.SingleResponse;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.DepartmentService;
import www.bwsensing.com.api.StructureService;
import www.bwsensing.com.dto.clientobject.SystemStructureCO;
import www.bwsensing.com.dto.command.DepartmentSaveCmd;
import www.bwsensing.com.dto.command.DepartmentUpdateCmd;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;


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
