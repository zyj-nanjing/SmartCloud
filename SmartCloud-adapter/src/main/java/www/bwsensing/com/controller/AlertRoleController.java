package www.bwsensing.com.controller;

import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.AlertRoleService;
import www.bwsensing.com.dto.command.AlertRoleAddCmd;
import www.bwsensing.com.dto.command.AlertRoleBindCmd;
import www.bwsensing.com.dto.command.AlertRoleUpdateCmd;
import www.bwsensing.com.dto.clientobject.AlertRoleCO;
import www.bwsensing.com.dto.command.query.AlertRoleQuery;
import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "告警规则管理")
@RequestMapping("/api/v1.0/alert/role")
@RestController
public class AlertRoleController {
    @Autowired
    private AlertRoleService alertRoleService;

    @ApiOperation("传感器与告警模板绑定")
    @PostMapping("/bind")
    public Response sensorAlertBind(@Valid @RequestBody AlertRoleBindCmd bindCmd){
        return alertRoleService.sensorAlertBind(bindCmd);
    }

    @ApiOperation("添加告警规则")
    @PostMapping("/add")
    public Response addAlertRole(@Valid @RequestBody AlertRoleAddCmd saveCmd){
        return alertRoleService.saveAlertRole(saveCmd);
    }

    @ApiOperation("修改告警规则")
    @PostMapping("/update")
    public Response updateAlertRole(@RequestBody AlertRoleUpdateCmd updateCmd){
        return alertRoleService.updateAlertRole(updateCmd);
    }

    @ApiOperation("告警规则查询(分页)")
    @PostMapping("/page/query")
    public PageResponse<AlertRoleCO> selectAlertRole(@RequestBody AlertRoleQuery query){
        return alertRoleService.selectAlertRole(query);
    }
    @ApiOperation("删除规则")
    @GetMapping("/delete/{roleId}")
    public Response deleteAlertRole(@PathVariable Integer roleId){
        return alertRoleService.deleteAlertRole(roleId);
    }
}
