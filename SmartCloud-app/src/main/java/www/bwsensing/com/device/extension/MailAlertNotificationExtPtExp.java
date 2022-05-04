package www.bwsensing.com.device.extension;

import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;
import java.net.MalformedURLException;
import org.springframework.util.Assert;
import java.nio.charset.StandardCharsets;
import com.alibaba.cola.extension.Extension;
import com.alibaba.cola.exception.BizException;
import www.bwsensing.com.common.utills.io.IoUtils;
import www.bwsensing.com.common.mail.IMailService;
import www.bwsensing.com.common.core.text.Convert;
import www.bwsensing.com.common.utills.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import www.bwsensing.com.common.constant.BizScenarioCode;
import www.bwsensing.com.common.cache.redis.RedisService;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import www.bwsensing.com.common.mail.database.MailConfigMapper;
import www.bwsensing.com.common.mail.database.MailTemplateMapper;
import www.bwsensing.com.common.mail.convertor.MailConfigConvertor;
import www.bwsensing.com.device.dto.command.NotificationMessageCmd;
import www.bwsensing.com.device.extensionpoint.AlertNotificationExtPt;
import www.bwsensing.com.device.gatewayimpl.database.AlertGroupMapper;
import www.bwsensing.com.common.mail.database.dataobject.MailConfigDO;
import www.bwsensing.com.common.mail.database.dataobject.MailTemplateDO;


/**
 * @author macos-zyj
 */
@Slf4j
@Extension(bizId = BizScenarioCode.BIZ_ID_CLOUD,
        useCase = BizScenarioCode.USER_CAUSE_NOTIFICATION,scenario = BizScenarioCode.BATCH_MAIL_SCENARIO)
public class MailAlertNotificationExtPtExp implements AlertNotificationExtPt {
    private static final String DEFAULT_MESSAGE_CONDITION = "mail_notification";
    /**Redis服务*/
    @Resource
    private RedisService redisService;
    @Resource
    private IMailService mailService;
    @Resource
    private MailConfigMapper mailConfigMapper;
    @Resource
    private MailTemplateMapper mailTemplateMapper;
    @Resource
    private AlertGroupMapper alertGroupMapper;

    @Override
    public void singleNotification(NotificationMessageCmd message, Integer userId) {

    }

    @Override
    public void singleNotification(NotificationMessageCmd message) {
        NotificationMessageCmd.Mail mailFile = new NotificationMessageCmd.Mail();
        SingleSendMailRequest sendRequest = initBatchSendMail(message,mailFile);
        String fileKey = mailFile.getFileKey();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(message.getAlertTime());
        String httpBody = getHtmlBodyByUrl(fileKey,mailFile.getFileLocation())
                .replace("[SENSOR_NAME]", message.getSensorName())
                .replace("[SENSOR_MODEL]",message.getSensorModel())
                .replace("[CURRENT_PROJECT]",message.getCurrentProject())
                .replace("[ALERT_GROUP_NAME]",message.getAlertGroupName())
                .replace("[SENSOR_SN]",message.getSensorSn())
                .replace("[ALERT_NAME]",message.getAlertName())
                .replace("[ALERT_TIME]",dateString)
                .replace("[ALERT_MESSAGE]",message.getAlertMessage());
        sendRequest.setHtmlBody(httpBody);
        try {
            mailService.sendMailRequest(sendRequest);
        } catch (Exception ex){
            log.warn("Alert notification mail send error ;error message:{}",ex.getLocalizedMessage());
            throw new BizException("ALERT_NOTIFICATION_SEND_ERROR",ex.getLocalizedMessage());
        }
    }

    @Override
    public void batchNotification(NotificationMessageCmd message) {

    }


    /**
     * 通过OSS软链接方式获取Html发送体内容
     * @param fileKey 文件索引
     * @param url 文件路径
     * @return
     */
    private String getHtmlBodyByUrl(String fileKey,String url)  {
        try{
            InputStream mailTemplateStream = checkCacheAndGetInput(fileKey,url);
            return getInputStreamToHtmlBody(mailTemplateStream);
        }catch (MalformedURLException urlException){
            log.warn("malformed url exception:{}", ExceptionUtils.getStackTrace(urlException));
            throw new BizException("路径解析失败！");
        } catch (IOException ioException) {
            log.warn("IO exception:{}",ExceptionUtils.getStackTrace(ioException));
            throw new BizException("IO异常！");
        }
    }

    private SingleSendMailRequest initBatchSendMail(NotificationMessageCmd message,NotificationMessageCmd.Mail mailFile){
        MailConfigDO mailConfigDO = mailConfigMapper.selectConfigByName(DEFAULT_MESSAGE_CONDITION);
        MailTemplateDO mailTemplate = mailTemplateMapper.selectTemplateByName(DEFAULT_MESSAGE_CONDITION);
        Assert.notNull(mailConfigDO,"请检查参数配置");
        Assert.notNull(mailTemplate,"请检查参数配置");
        SingleSendMailRequest singleMailRequest = MailConfigConvertor.initNoReplyRequest(mailConfigDO);
        singleMailRequest.setToAddress(getToAddress(message.getAlertGroupId()));
        mailFile.setFileKey(mailTemplate.getFileName()+mailTemplate.getVersion());
        mailFile.setFileLocation(mailTemplate.getTemplateLocal());
        return singleMailRequest;
    }

    private String getToAddress(Integer alertGroupId){
        List<String> emailAddress = alertGroupMapper.getGroupNotificationMembers(alertGroupId);
        Assert.notEmpty(emailAddress,"邮件通知列表不能为空!");
        StringBuilder toAddress = new StringBuilder();
        emailAddress.forEach(address -> {
            if(StringUtils.isEmpty(toAddress.toString())){
                toAddress.append(address);
            } else{
                toAddress.append(",").append(address);
            }
        });
        return toAddress.toString();
    }


    private InputStream checkCacheAndGetInput(String fileKey,String url) throws IOException {
        String cacheResult = redisService.getCacheObject(fileKey);
        if (StringUtils.isNotEmpty(cacheResult)){
            redisService.setCacheObject(fileKey,cacheResult,1L, TimeUnit.DAYS);
        }
        else {
            // 上传网络流。
            cacheResult = Convert.str(IoUtils.toByteArray(new URL(url).openStream()),"utf-8");
            redisService.setCacheObject(fileKey,cacheResult,3L, TimeUnit.DAYS);
        }
        return new ByteArrayInputStream(cacheResult.getBytes());
    }


    private String getInputStreamToHtmlBody(InputStream mailTemplateStream){
        Scanner scanner = new Scanner(mailTemplateStream, String.valueOf(StandardCharsets.UTF_8));
        StringBuilder htmlBody = new StringBuilder();
        while (scanner.hasNextLine()) {
            //去除空格 否则调用接口会报异常
            String currentLine= scanner.nextLine().replace("  ", "");
            htmlBody.append(currentLine);
        }
        return htmlBody.toString();
    }

}
