package www.bwsensing.com.device.extension;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.cola.extension.Extension;
import www.bwsensing.com.common.cache.redis.RedisService;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.sms.database.SmsConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import www.bwsensing.com.device.dto.command.NotificationMessageCmd;
import www.bwsensing.com.device.extensionpoint.AlertNotificationExtPt;


/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,
        useCase = BizScenarioCode.USER_CAUSE_NOTIFICATION,scenario = BizScenarioCode.BATCH_SMS_SCENARIO)
public class SmsAlertNotificationExtPtExp implements AlertNotificationExtPt {
    @Resource
    private RedisService redisService;


    @Autowired
    private SmsConfigMapper configMapper;

    @Override
    public void singleNotification(NotificationMessageCmd message, Integer userId) {

    }

    @Override
    public void singleNotification(NotificationMessageCmd message) {

    }

    @Override
    public void batchNotification(NotificationMessageCmd message) {

    }
}
