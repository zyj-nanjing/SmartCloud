package www.bwsensing.com.system.web;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.SingleResponse;
import io.swagger.annotations.ApiOperation;
import www.bwsensing.com.system.api.SystemMenuService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import www.bwsensing.com.domain.system.gateway.PermissionGateway;
import www.bwsensing.com.system.dto.clientobject.SystemMenuCO;
import www.bwsensing.com.system.dto.command.SystemMenuSaveCmd;
import www.bwsensing.com.system.dto.command.SystemMenuUpdateCmd;


/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api( tags = "系统菜单管理")
@RequestMapping("/api/v1.0/system/menu")
@RestController
public class SystemMenuController {
    @Autowired
    private SystemMenuService viewMenuService;

    @Autowired
    private PermissionGateway permissionGateway;

    @ApiOperation("获取菜单详情")
    @GetMapping("/query/{mid}")
    public SingleResponse<SystemMenuCO> querySystemMenuById(@PathVariable Integer mid){
        return  viewMenuService.querySystemMenuById(mid);
    }

    @ApiOperation("系统菜单树")
    @PostMapping("/tree/show")
    public MultiResponse<SystemMenuCO> listMenuTree(){
        return viewMenuService.showSystemMenus();
    }

    @ApiOperation("创建菜单页")
    @PostMapping("/save")
    public Response addSystemMenu(@Valid @RequestBody SystemMenuSaveCmd saveCmd){
        return viewMenuService.addSystemMenu(saveCmd);
    }

    @ApiOperation("修改菜单页")
    @PostMapping("/update")
    public Response updateSystemMenu(@Valid @RequestBody SystemMenuUpdateCmd updateCmd){
        return viewMenuService.updateSystemMenu(updateCmd);
    }

    @ApiOperation("同步权限")
    @GetMapping("/synchronization")
    public  Response synchronizationPermission(){
        permissionGateway.synchronizationPermission();
        return Response.buildSuccess();
    }

    public Response deleteSystemMenu(Integer menuId){
        return Response.buildSuccess();
    }
}
