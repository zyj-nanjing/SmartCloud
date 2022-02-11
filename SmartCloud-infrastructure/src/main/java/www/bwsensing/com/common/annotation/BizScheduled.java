package www.bwsensing.com.common.annotation;

import java.lang.annotation.*;

/**
 * 业务 定时事务
 * @author macos-zyj
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BizScheduled {

    /**
     * 定时事务名称
     * @return 事务名称
     */
    String scheduledName() default "DEFAULT_SCHEDULE";

    /**
     * Code 编码
     * @return
     */
    String scheduleCode();

    /**
     * 检查时间间隔 超过间隔后直接调用下一层级
     * @return 检查时间间隔
     */
    int checkInterval() default 1000;


}
