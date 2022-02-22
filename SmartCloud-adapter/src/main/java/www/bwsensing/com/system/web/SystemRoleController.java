package www.bwsensing.com.system.web;

import javax.validation.Valid;
import javax.annotation.Resource;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.system.api.SystemRoleService;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.system.dto.clientobject.SystemRoleCO;
import www.bwsensing.com.system.dto.command.SystemRoleSaveCmd;
import www.bwsensing.com.system.dto.command.SystemRoleUpdateCmd;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.project.dto.command.query.SystemRolePageQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api( tags = "系统角色管理")
@RequestMapping("/api/v1.0/system/role")
@RestController
public class SystemRoleController {
    @Resource
    private SystemRoleService roleService;

    @ApiOperation("新增角色")
    @PostMapping("/save")
    public Response save(@Valid @RequestBody SystemRoleSaveCmd saveCmd){
        return roleService.saveRole(saveCmd);
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public Response update(@Valid @RequestBody SystemRoleUpdateCmd updateCmd){
        return roleService.updateRole(updateCmd);
    }

    @ApiOperation("查询角色详情")
    @GetMapping("/single/query/{roleId}")
    public SingleResponse<SystemRoleCO> queryRoleById(@PathVariable Integer roleId){
        return roleService.queryRoleInfoById(roleId);
    }

    @ApiOperation("查询角色列表(分页)")
    @PostMapping("/page/query")
    public PageResponse<SystemRoleCO> queryRolePage(@RequestBody SystemRolePageQuery rolePageQuery){
        return roleService.querySystemRolePage(rolePageQuery);
    }
}
