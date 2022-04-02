package www.bwsensing.com.device.extension;

import java.util.List;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import com.alibaba.fastjson.JSON;
import org.springframework.util.Assert;
import com.alibaba.cola.extension.Extension;
import www.bwsensing.com.common.sms.ISmsService;
import www.bwsensing.com.common.utills.StringUtils;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.sms.database.SmsConfigMapper;
import www.bwsensing.com.common.sms.database.dataobject.SmsConfigDO;
import www.bwsensing.com.device.dto.command.NotificationMessageCmd;
import www.bwsensing.com.device.extensionpoint.AlertNotificationExtPt;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.device.extension.model.SmsNotificationTemplateJson;


/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,
        useCase = BizScenarioCode.USER_CAUSE_NOTIFICATION,scenario = BizScenarioCode.BATCH_SMS_SCENARIO)
public class SmsAlertNotificationExtPtExp implements AlertNotificationExtPt {
    private static final String DEFAULT_MESSAGE_CONDITION = "告警推送";

    @Resource
    private SmsConfigMapper configMapper;

    @Resource
    private ISmsService smsService;

    @Resource
    private AlertGroupMapper alertGroupMapper;

    @Override
    public void singleNotification(NotificationMessageCmd message, Integer userId) {

    }

    @Override
    public void singleNotification(NotificationMessageCmd message)  {
        SmsConfigDO smsConfig = configMapper.selectSmsConfigByConfigName(DEFAULT_MESSAGE_CONDITION);
        String phoneNumber = getToAddress(message.getAlertGroupId());
        SendSmsRequest smsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(smsConfig.getSignName())
                .setTemplateCode(smsConfig.getTemplateCode())
                .setTemplateParam(getNotificationParam(message));
        try{
            smsService.sendSmsRequest(smsRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BizException(ex.getLocalizedMessage());
        }
    }

    @Override
    public void batchNotification(NotificationMessageCmd message) {

    }


    private String getNotificationParam(NotificationMessageCmd message){
        SmsNotificationTemplateJson notificationTemplate = new SmsNotificationTemplateJson();
        notificationTemplate.setAlertName(message.getAlertName());
        notificationTemplate.setModel(message.getSensorModel());
        notificationTemplate.setTime(message.getAlertTime());
        notificationTemplate.setProject(message.getCurrentProject());
        notificationTemplate.setUniqueCode(message.getSensorSn());
        return JSON.toJSONString(notificationTemplate);
    }


    private String getToAddress(Integer alertGroupId){
        List<String> mobileAddress = alertGroupMapper.getNotificationPhones(alertGroupId);
        Assert.notEmpty(mobileAddress,"短信通知列表不能为空!");
        StringBuilder toAddress = new StringBuilder();
        mobileAddress.forEach(address -> {
            if(StringUtils.isEmpty(toAddress.toString())){
                toAddress.append(address);
            } else{
                toAddress.append(",").append(address);
            }
        });
        return toAddress.toString();
    }
}
