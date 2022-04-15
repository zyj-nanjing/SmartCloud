package www.bwsensing.com.device.eventhandle;

import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.exception.Assert;
import org.springframework.beans.BeanUtils;
import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.extension.BizScenario;
import www.bwsensing.com.common.utills.Md5Utils;
import www.bwsensing.com.device.api.NotificationService;
import com.alibaba.cola.extension.ExtensionExecutor;
import www.bwsensing.com.common.core.event.EventHandler;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.core.event.EventHandlerI;
import www.bwsensing.com.device.dto.command.NotificationMessageCmd;
import www.bwsensing.com.device.extensionpoint.AlertNotificationExtPt;
import www.bwsensing.com.domain.device.model.alert.NotificationMethod;
import www.bwsensing.com.domainevent.AlertNotificationPushEvent;
import www.bwsensing.com.common.constant.NotificationLimitConstant;

/**
 * 告警消息结束以及事件推送
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
    private NotificationService notificationService;

    @Override
    public Response execute(AlertNotificationPushEvent pushEvent) {
        NotificationMessageCmd  messagePushCmd = new NotificationMessageCmd();
        Assert.notEmpty(pushEvent.getPushMethods(),"发送方式不能为空!");
        BeanUtils.copyProperties(pushEvent,messagePushCmd);
        notificationService.cacheNotification(pushEvent.getAlertGroupId(), pushEvent.getAlertMessage());
        //扩展点 多告警方式进行推送
        if (!checkPushIsLimit(pushEvent.getAlertGroupId())){
            for (Integer typeId:pushEvent.getPushMethods()) {
                String scenarioCode = BizScenarioCode.getNotification(NotificationMethod.getNotificationMethod(typeId));
                BizScenario scenario = BizScenario.valueOf(BizScenarioCode.BIZ_ID_CLOUD, BizScenarioCode.USER_CAUSE_NOTIFICATION,scenarioCode);
                messagePushCmd.setBizScenario(scenario);
                extensionExecutor.executeVoid(AlertNotificationExtPt.class, messagePushCmd.getBizScenario(), extension -> extension.singleNotification(messagePushCmd));
            }
            putNotificationLimit(pushEvent.getAlertGroupId());
        }
        return Response.buildSuccess();
    }

    private Boolean checkPushIsLimit(Integer groupId){
        String checkKey = NotificationLimitConstant.PREFIX + groupId;
        String securityKey = Md5Utils.encryptMd5(checkKey);
        if( null == redisService.getCacheObject(securityKey)){
            return false;
        } else {
            Integer count = redisService.getCacheObject(securityKey);
            return count > NotificationLimitConstant.MAX_COUNT;
        }
    }

    private void putNotificationLimit(Integer groupId){
        String checkKey = NotificationLimitConstant.PREFIX + groupId;
        String securityKey = Md5Utils.encryptMd5(checkKey);
        if( null == redisService.getCacheObject(securityKey)){
            redisService.setCacheObject(securityKey,1, NotificationLimitConstant.CHECK_LAST, NotificationLimitConstant.TIME_UNIT);
        } else {
            Integer count = redisService.getCacheObject(securityKey);
            redisService.setCacheObject(securityKey,count+1, NotificationLimitConstant.CHECK_LAST, NotificationLimitConstant.TIME_UNIT);
        }
    }
}
