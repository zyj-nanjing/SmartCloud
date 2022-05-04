package www.bwsensing.com.device.extension.model;

import lombok.Data;
import www.bwsensing.com.common.sms.SmsTemplateJson;

/**
 * @author macos-zyj
 */@Data
public class SmsNotificationTemplateJson extends SmsTemplateJson {
    /**
     * 当前项目名称
     */
    private String project;

    /**
     * 传感器型号
     */
    private String model;

    /**
     * 触发时间
     */
    private String time;

    /**
     * 告警名称
     */
     private String alertName;

    /**
     * 唯一编码
     */
     private String uniqueCode;
}
