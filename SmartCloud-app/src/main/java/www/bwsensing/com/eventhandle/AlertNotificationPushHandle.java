package www.bwsensing.com.eventhandle;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import com.alibaba.cola.extension.BizScenario;
import com.alibaba.cola.extension.ExtensionExecutor;
import org.springframework.beans.BeanUtils;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.domain.device.alert.NotificationMethod;
import www.bwsensing.com.domainevent.AlertNotificationPushEvent;
import www.bwsensing.com.dto.command.NotificationMessageCmd;
import www.bwsensing.com.extensionpoint.AlertNotificationExtPt;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class AlertNotificationPushHandle implements EventHandlerI<Response, AlertNotificationPushEvent> {
    @Resource
    private ExtensionExecutor extensionExecutor;

    @Override
    public Response execute(AlertNotificationPushEvent pushEvent) {
        NotificationMessageCmd  messagePushCmd = new NotificationMessageCmd();
        Assert.notNull(pushEvent.getPushMethod(),"发送方式不能为空!");
        BeanUtils.copyProperties(pushEvent,messagePushCmd);
        String scenarioCode = BizScenarioCode.getNotification(NotificationMethod.getNotificationMethod(pushEvent.getPushMethod()));
        BizScenario scenario = BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, BizScenarioCode.USER_CAUSE_NOTIFICATION,scenarioCode);
        messagePushCmd.setBizScenario(scenario);
        //扩展点 多重预警方式推送
        extensionExecutor.executeVoid(AlertNotificationExtPt.class, messagePushCmd.getBizScenario(), extension -> extension.singleNotification(messagePushCmd));
        return Response.buildSuccess();
    }
}
