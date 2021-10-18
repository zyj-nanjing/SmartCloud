package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IRoleService;
import www.bwsensing.com.dto.command.RoleSaveCmd;
import www.bwsensing.com.dto.command.RoleUpdateCmd;
import www.bwsensing.com.dto.command.query.SystemRolePageQuery;
import www.bwsensing.com.dto.clientobject.SystemRoleCO;
import javax.annotation.Resource;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/role")
@RestController
public class RoleController {
    @Resource
    private IRoleService roleService;

    @PostMapping("/save")
    public Response save(@RequestBody RoleSaveCmd saveCmd){
        return roleService.saveRole(saveCmd);
    }

    @PostMapping("/update")
    public Response update(@RequestBody RoleUpdateCmd updateCmd){
        return roleService.updateRole(updateCmd);
    }

    @GetMapping("/root/query")
    public MultiResponse<SystemRoleCO> selectRootRoles(){
        return roleService.selectRootRoles();
    }

    @PostMapping("/query/page")
    public PageResponse<SystemRoleCO> queryRolePage(@RequestBody SystemRolePageQuery rolePageQuery){
        return roleService.querySystemRolePage(rolePageQuery);
    }

}
