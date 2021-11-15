package www.bwsensing.com.eventhandle;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import www.bwsensing.com.common.utills.Md5Utils;
import www.bwsensing.com.api.INotificationService;
import www.bwsensing.com.common.redis.RedisService;
import com.alibaba.cola.extension.ExtensionExecutor;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.dto.command.NotificationMessageCmd;
import www.bwsensing.com.domain.device.alert.NotificationMethod;
import www.bwsensing.com.domainevent.AlertNotificationPushEvent;
import www.bwsensing.com.extensionpoint.AlertNotificationExtPt;
import www.bwsensing.com.common.constant.NotificationLimitConstant;
/**
 * @author macos-zyj
 */
@CatchAndLog
@Slf4j
@EventHandler
public class AlertNotificationPushHandle implements EventHandlerI<Response, AlertNotificationPushEvent> {
    @Resource
    private ExtensionExecutor extensionExecutor;
    @Resource
    private RedisService redisService;
    @Resource
    private INotificationService notificationService;

    @Override
    public Response execute(AlertNotificationPushEvent pushEvent) {
        NotificationMessageCmd  messagePushCmd = new NotificationMessageCmd();
        Assert.notNull(pushEvent.getPushMethod(),"发送方式不能为空!");
        BeanUtils.copyProperties(pushEvent,messagePushCmd);
        String scenarioCode = BizScenarioCode.getNotification(NotificationMethod.getNotificationMethod(pushEvent.getPushMethod()));
        BizScenario scenario = BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, BizScenarioCode.USER_CAUSE_NOTIFICATION,scenarioCode);
        messagePushCmd.setBizScenario(scenario);
        notificationService.cacheNotification(pushEvent.getAlertGroupId(), pushEvent.getAlertMessage());
        //扩展点 多重预警方式推送
        if (!chekPushIsLimit(pushEvent.getAlertGroupId())){
            extensionExecutor.executeVoid(AlertNotificationExtPt.class, messagePushCmd.getBizScenario(), extension -> extension.singleNotification(messagePushCmd));
            putNotificationLimit(pushEvent.getAlertGroupId());
        }
        return Response.buildSuccess();
    }

    private Boolean chekPushIsLimit(Integer groupId){
        String chekKey = NotificationLimitConstant.PREFIX + groupId;
        String securityKey = Md5Utils.encryptMd5(chekKey);
        if( null == redisService.getCacheObject(securityKey)){
            return false;
        } else {
            Integer count = redisService.getCacheObject(securityKey);
            return count > NotificationLimitConstant.MAX_COUNT;
        }
    }
    private void putNotificationLimit(Integer groupId){
        String chekKey = NotificationLimitConstant.PREFIX + groupId;
        String securityKey = Md5Utils.encryptMd5(chekKey);
        if( null == redisService.getCacheObject(securityKey)){
            redisService.setCacheObject(securityKey,1, NotificationLimitConstant.CHECK_LAST, NotificationLimitConstant.TIME_UNIT);
        } else {
            Integer count = redisService.getCacheObject(securityKey);
            redisService.setCacheObject(securityKey,count+1, NotificationLimitConstant.CHECK_LAST, NotificationLimitConstant.TIME_UNIT);
        }
    }


}
