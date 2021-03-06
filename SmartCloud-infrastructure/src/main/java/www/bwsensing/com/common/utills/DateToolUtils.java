package www.bwsensing.com.common.utills;

import afu.org.checkerframework.checker.nullness.qual.Nullable;
import com.alibaba.cola.exception.SysException;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.annotation.Contract;
import org.hibernate.validator.constraints.Range;
import org.quartz.CronExpression;
import java.lang.management.ManagementFactory;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * 时间工具类
 *
 * @author XianYun
 */
public class DateToolUtils extends org.apache.commons.lang3.time.DateUtils
{

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String SYMPTOMS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    private static final String[] PARSE_PATTERNS = {
            YYYY_MM_DD, YYYY_MM_DD_HH_MM_SS, "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     *
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     *
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static String dateTimeNow()
    {
        return dateTimeNow(SYMPTOMS);
    }

    public static String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new SysException(e.getMessage());
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), PARSE_PATTERNS);
        }
        catch (ParseException e)
        {
            return null;
        }
    }

    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    public static Temporal getDateTimeTo(String formatTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
        return LocalDate.parse(formatTime, formatter);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000L * 24L * 60L * 60L;
        long nh = 1000L * 60L * 60L;
        long nm = 1000L * 60L;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    /**
     * 计算两个时间差
     */
    public static Long getDateMinPoor(Date endDate, Date nowDate)
    {
        long nd = 1000L * 24L * 60L * 60L;
        long nh = 1000L * 60L * 60L;
        long nm = 1000L * 60L;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        // 计算差多少秒//输出结果
        return diff % nd % nh / nm;
    }
}
