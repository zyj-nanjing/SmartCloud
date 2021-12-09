package www.bwsensing.com.controller;

import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import com.alibaba.cola.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.IAlertGroupService;
import www.bwsensing.com.dto.clientobject.AlertGroupCO;
import www.bwsensing.com.dto.clientobject.NotificationMemberBindCO;
import www.bwsensing.com.dto.command.AlertGroupSaveCmd;
import www.bwsensing.com.dto.command.AlertGroupUpdateCmd;
import www.bwsensing.com.dto.command.NotificationMemberCmd;
import www.bwsensing.com.dto.command.query.AlertGroupQuery;

import javax.validation.Valid;


/**
 * 预警分组实例化
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/alert/group")
@RestController
public class AlertGroupController {
    @Autowired
    private IAlertGroupService alertGroupService;

    @PostMapping("/save")
    public Response saveAlertGroup(@Valid @RequestBody AlertGroupSaveCmd saveCmd){
        return alertGroupService.saveAlertGroup(saveCmd);
    }

    @GetMapping("/bind/query/{groupId}")
    public MultiResponse<NotificationMemberBindCO> queryNotificationMember(@PathVariable Integer groupId){
        return alertGroupService.queryNotificationMember(groupId);
    }
    @PostMapping("/bind/save")
    public Response saveNotificationMember(@Valid @RequestBody NotificationMemberCmd saveCmd){
        return alertGroupService.saveNotificationMember(saveCmd);
    }

    @PostMapping("/update")
    public Response updateAlertGroup(@Valid @RequestBody AlertGroupUpdateCmd updateCmd){
        return alertGroupService.updateAlertGroup(updateCmd);
    }

    @PostMapping("/page/query")
    public PageResponse<AlertGroupCO> alertGroupPageQuery(@Valid @RequestBody AlertGroupQuery groupQuery){
        return alertGroupService.alertGroupPageQuery(groupQuery);
    }

    @GetMapping("/delete/{id}")
    public Response deleteAlertGroup(@PathVariable Integer id){
        return alertGroupService.deleteAlertGroup(id);
    }
}
