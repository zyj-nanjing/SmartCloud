package www.bwsensing.com.device.web;

import javax.validation.Valid;
import io.swagger.annotations.Api;
import com.alibaba.cola.dto.Response;
import io.swagger.annotations.ApiOperation;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.PageResponse;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.device.api.NotificationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.clientobject.NotificationCO;
import www.bwsensing.com.device.dto.clientobject.NotificationMsgCO;
import www.bwsensing.com.device.dto.command.NotificationUpdateCmd;
import www.bwsensing.com.device.dto.command.query.NotificationQuery;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@Api(tags = "通知管理")
@RequestMapping("/api/v1.0/alert/notification")
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @ApiOperation("通知查询(分页)")
    @PostMapping("/page/query")
    public PageResponse<NotificationCO> selectNotificationByPage(@Valid @RequestBody NotificationQuery pageQuery){
        return notificationService.selectNotificationByPage(pageQuery);
    }
    @ApiOperation("通知标签查询(分页)")
    @GetMapping("/tag/query")
    public MultiResponse<NotificationMsgCO> queryNotification(){
        return notificationService.getCurrentMessage();
    }

    @ApiOperation("通知标签修改")
    @PostMapping("/tag/update")
    public Response updateMessageStatus(@Valid @RequestBody NotificationUpdateCmd updateUpdateCmd){
        return notificationService.updateCacheStatus(updateUpdateCmd.getUpdateIds());
    }
}
