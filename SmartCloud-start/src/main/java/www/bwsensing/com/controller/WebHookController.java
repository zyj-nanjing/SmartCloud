package www.bwsensing.com.controller;

import java.util.Date;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import reactor.core.publisher.Flux;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import www.bwsensing.com.domain.device.alert.AlertNotification;
import www.bwsensing.com.domain.device.alert.webhook.Notification;
import www.bwsensing.com.domain.gateway.AlertNotificationGateway;
import www.bwsensing.com.domain.device.alert.webhook.AlertManager;

/**
 * @author macos-zyj
 */
@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/main/api")
public class WebHookController {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssXXX";
    @Autowired
    private AlertNotificationGateway notificationGateway;

    @PostMapping("/webhook")
    public Flux<AlertManager> webhook(@RequestBody AlertManager alertmanager) throws ParseException {
        log.debug(alertmanager.toString());
        for (AlertManager.Alert alert : alertmanager.alerts) {
            String status = alert.status;
            String info = alert.annotations.info;
            String summary = alert.annotations.summary;
            String rfc3339 = alert.startsAt.replaceAll("\\.([0-9]+)", "");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            Date datetime = simpleDateFormat.parse(rfc3339);
            Notification notification = new Notification(status, datetime, summary);
            log.debug("startsAt Data: {}", alert.startsAt);
            log.debug("RFC3339 Data: {}", rfc3339);
            log.info(notification.toString());
            notificationGateway.receiveNotification(new AlertNotification(info,summary,datetime));
        }
        return Flux.just(alertmanager);
    }
}
