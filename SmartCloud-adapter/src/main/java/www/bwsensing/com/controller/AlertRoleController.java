package www.bwsensing.com.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IAlertRoleService;
import www.bwsensing.com.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.dto.command.AlertRoleBindCmd;
import www.bwsensing.com.dto.command.AlertRoleUpdateCmd;
import www.bwsensing.com.dto.command.query.AlertRoleQuery;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/alert/role")
@RestController
public class AlertRoleController {
    @Autowired
    private IAlertRoleService alertRoleService;


    @PostMapping("/bind")
    public Response sensorAlertBind(@Valid @RequestBody AlertRoleBindCmd bindCmd){
        return alertRoleService.sensorAlertBind(bindCmd);
    }

    @PostMapping("/add")
    public Response addAlertRole(@Valid @RequestBody AlertRoleAddCmd saveCmd){
        return alertRoleService.saveAlertRole(saveCmd);
    }

    @PostMapping("/update")
    public Response updateAlertRole(@RequestBody AlertRoleUpdateCmd updateCmd){
        return alertRoleService.updateAlertRole(updateCmd);
    }

    @PostMapping("/page/query")
    public PageResponse<AlertRoleCO> selectAlertRole(@RequestBody AlertRoleQuery query){
        return alertRoleService.selectAlertRole(query);
    }

    @GetMapping("/delete/{roleId}")
    public Response deleteAlertRole(@PathVariable Integer roleId){
        return alertRoleService.deleteAlertRole(roleId);
    }
}
