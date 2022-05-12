package www.bwsensing.com.common.utills;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

/**
 * @author 向振华
 * @date 2021/09/13 09:33
 */
public class CronDateUtils {

    private static final CronDefinition CRON_DEFINITION = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);

    public static LocalDateTime lastExecution(String expression) {
        return lastExecution(expression,LocalDateTime.now());
    }

    public static LocalDateTime lastExecution(String expression, LocalDateTime localDateTime) {
        CronParser parser = new CronParser(CRON_DEFINITION);
        Cron quartzCron = parser.parse(expression);
        ZonedDateTime now = ZonedDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.ofHours(8)), ZoneId.systemDefault());
        ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);
        Optional<ZonedDateTime> zonedDateTimeOptional = executionTime.lastExecution(now);
        if (zonedDateTimeOptional.isPresent()) {
            ZonedDateTime zonedDateTime = zonedDateTimeOptional.get();
            return zonedDateTime.toLocalDateTime();
        }
        return LocalDateTime.now();
    }


    public static LocalDateTime nextExecution(String expression) {
        return nextExecution(expression,LocalDateTime.now());
    }
    public static LocalDateTime nextExecution(String expression, LocalDateTime localDateTime) {
        CronParser parser = new CronParser(CRON_DEFINITION);
        Cron quartzCron = parser.parse(expression);
        ZonedDateTime now = ZonedDateTime.ofInstant(localDateTime.toInstant(ZoneOffset.ofHours(8)), ZoneId.systemDefault());
        ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);
        Optional<ZonedDateTime> zonedDateTimeOptional = executionTime.nextExecution(now);
        if (zonedDateTimeOptional.isPresent()) {
            ZonedDateTime zonedDateTime = zonedDateTimeOptional.get();
            return zonedDateTime.toLocalDateTime();
        }
        return LocalDateTime.now();
    }

}