package www.bwsensing.com.controller;

import com.alibaba.cola.dto.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.api.INotificationService;
import www.bwsensing.com.dto.clientobject.NotificationCO;
import www.bwsensing.com.dto.command.query.NotificationQuery;

import javax.validation.Valid;

/**
 * @author macos-zyj
 */
@Validated
@CrossOrigin
@RequestMapping("/api/v1.0/alert/notification")
@RestController
public class NotificationController {

    @Autowired
    private INotificationService notificationService;

    @PostMapping("/page/query")
    public PageResponse<NotificationCO> selectNotificationByPage(@Valid @RequestBody NotificationQuery pageQuery){
        return notificationService.selectNotificationByPage(pageQuery);
    }
}
