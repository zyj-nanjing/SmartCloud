package www.bwsensing.com.common.utills;

import com.alibaba.cola.exception.BizException;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * 格式校验工具类
 * @author mac_zyj
 */
public class FormatUtils {
    private static final Logger logger = LoggerFactory.getLogger(FormatUtils.class);
    private static final String ALGORITHM ="SHA1PRNG";

    /** 正则表达
     * 手机号码由11位数字组成，
     * 匹配格式：前三位固定格式+后8位任意数
     * 此方法中前三位格式有：
     * 13+任意数
     * 15+除4的任意数
     * 18+除1和4的任意数
     * 17+除9的任意数
     * 147
     * @param str 待校验结果
     * @return 结果
     */
    public static boolean isNumLegal(String str)  {
        try {
            String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$";
            Pattern p = Pattern.compile(regExp);
            Matcher m = p.matcher(str);
            return m.matches();
        }catch (PatternSyntaxException e){
            logger.info("格式错误");
        }
        return false;
    }

    /**
     * 校验是否是邮箱
     * @param str 待校验字符串
     * @return 结果
     */
    public static boolean isEmail(String str){
        try {
            String pattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
            Pattern p = Pattern.compile(pattern);
            Matcher matcher = p.matcher(str);
            return matcher.matches();
        }catch (PatternSyntaxException e){
            logger.info("格式错误");
        }
        return false;
    }


    public static String getOrganizationCode(){
        return RandomStringUtils.randomAlphanumeric(10);
    }

    /**
     * 获取加密后的手机号
     * @param phoneNumber 手机号
     * @return 加密后的结果
     */
    public static String getSecurityPhoneNumber(String phoneNumber){
        if (StringUtils.isNotEmpty(phoneNumber)&&isNumLegal(phoneNumber))
        {
            return phoneNumber.replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2");
        }
        //todo 将Biz异常转换为通用的手机号错误异常
        throw new BizException("手机号格式错误!");
    }

    /**
     * 获取现在时间
     * @return 返回字符串格式yyyyMMddHHmmss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatter.format(currentTime);
    }
    /**
     * 由年月日时分秒+3位随机数
     * 生成流水号
     * @return serialNumber;
     */
    public static String getSerialNumber()  {
        try {
            SecureRandom random = SecureRandom.getInstance(ALGORITHM);
            String t = getStringDate();
            int x=(int)random.nextLong();
            return t + x;
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            throw new BizException("流水号生成失败!");
        }
    }

    /**
     * 生成校验码
     * @return checkCode
     */
    public static String getCheckCode(){
        try {
            SecureRandom random = SecureRandom.getInstance(ALGORITHM);
            return String.valueOf((int)((random.nextDouble() * 9 + 1) * 100000));
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            throw new BizException("校验码生成失败!");
        }
    }


    public static String getTransactionId(){
        return UUID.randomUUID().toString();
    }

    public static String initUserDefaultUserName(){
        try {
            SecureRandom random = SecureRandom.getInstance(ALGORITHM);
            String currentDate= DateToolUtils.getDate().replace("-","");
            String end = String.valueOf((int)((random.nextDouble() * 9 + 1) * 10000));
            return currentDate + end;
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            throw new BizException("用户默认用户名生成失败!");
        }
    }

    public static String initDefaultUserNickName(){
        try {
            SecureRandom random = SecureRandom.getInstance(ALGORITHM);
            return "用户"+ (int) ((random.nextDouble() * 9 + 1) * 100000);
        }catch (NoSuchAlgorithmException noSuchAlgorithmException){
            throw new BizException("默认用户名生成失败!");
        }
    }

    public static String initInvitationCode(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

}
