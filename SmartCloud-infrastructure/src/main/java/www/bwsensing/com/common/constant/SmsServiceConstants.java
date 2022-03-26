package www.bwsensing.com.common.constant;

/**
 * @author mac_zyj
 * SMS 服务模块常量
 */
public class SmsServiceConstants {

    /**
     * 检查一段时间发送次数
     */
    public static final Integer CHECK_SMS_SEND_TIMES_TIME = 1 ;
    /**
     * 一段时间内短信发送次数限制
     */
    public static final Integer CHECK_SMS_SEND_TIMES_TIMES_LIMITS = 10;
    /**
     * 验证短信有效期（分钟）
     */
    public static final Integer SMS_USE_EXPIRATION = 5;

    /**
     * 验证短信Redis有效期（分钟）
     */
    public static final Integer SMS_REDIS_EXPIRATION = 1;
}
