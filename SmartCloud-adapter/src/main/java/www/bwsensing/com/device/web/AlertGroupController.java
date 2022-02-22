package www.bwsensing.com.device.web;

import javax.validation.Valid;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.AlertGroupService;
import www.bwsensing.com.device.dto.command.query.AlertGroupQuery;
import www.bwsensing.com.device.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.device.dto.command.AlertGroupUpdateCmd;
import www.bwsensing.com.device.dto.command.NotificationMemberCmd;
import www.bwsensing.com.device.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.device.dto.clientobject.NotificationMemberBindCO;


/**
 * 预警分组实例化
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "告警分组管理")
@RequestMapping("/api/v1.0/alert/group")
@RestController
public class AlertGroupController {
    @Autowired
    private AlertGroupService alertGroupService;

    @ApiOperation("新增告警分组数据")
    @PostMapping("/save")
    public Response saveAlertGroup(@Valid @RequestBody AlertGroupSaveCmd saveCmd){
        return alertGroupService.saveAlertGroup(saveCmd);
    }
    @ApiOperation("查询告警通知人员")
    @GetMapping("/bind/query/{groupId}")
    public MultiResponse<NotificationMemberBindCO> queryNotificationMember(@PathVariable Integer groupId){
        return alertGroupService.queryNotificationMember(groupId);
    }

    @ApiOperation("绑定人员")
    @PostMapping("/bind/save")
    public Response saveNotificationMember(@Valid @RequestBody NotificationMemberCmd saveCmd){
        return alertGroupService.saveNotificationMember(saveCmd);
    }

    @ApiOperation("修改告警分组")
    @PostMapping("/update")
    public Response updateAlertGroup(@Valid @RequestBody AlertGroupUpdateCmd updateCmd){
        return alertGroupService.updateAlertGroup(updateCmd);
    }
    @ApiOperation("告警分组(分页查询)")
    @PostMapping("/page/query")
    public PageResponse<AlertGroupCO> alertGroupPageQuery(@Valid @RequestBody AlertGroupQuery groupQuery){
        return alertGroupService.alertGroupPageQuery(groupQuery);
    }
    @ApiOperation("根据编号删除告警组")
    @GetMapping("/delete/{id}")
    public Response deleteAlertGroup(@PathVariable Integer id){
        return alertGroupService.deleteAlertGroup(id);
    }
}
