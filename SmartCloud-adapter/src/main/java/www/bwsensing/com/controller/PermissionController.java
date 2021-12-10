package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IPermissionService;
import www.bwsensing.com.dto.command.query.BaseQuery;
import www.bwsensing.com.dto.command.PermissionSaveCmd;
import www.bwsensing.com.dto.command.PermissionUpdateCmd;
import www.bwsensing.com.dto.clientobject.PermissionCO;
import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/permission")
@RestController
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @PostMapping("/save")
    public Response save(@Valid @RequestBody PermissionSaveCmd saveCmd){
        return permissionService.savePermission(saveCmd);
    }

    @PostMapping("/update")
    public Response update(@Valid @RequestBody PermissionUpdateCmd updateCmd){
        return permissionService.updatePermission(updateCmd);
    }

    @GetMapping("/query/page")
    public PageResponse<PermissionCO> selectPage( @RequestBody BaseQuery baseQuery){
        return permissionService.selectPermissionPage(baseQuery);
    }

    @GetMapping("/query")
    public MultiResponse<PermissionCO> selectAllPermission(){
        return permissionService.selectMultiPermission();
    }
}
