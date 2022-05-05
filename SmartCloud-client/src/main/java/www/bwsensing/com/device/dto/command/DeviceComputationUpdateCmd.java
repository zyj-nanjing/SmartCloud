package www.bwsensing.com.device.dto.command;

import lombok.Data;
import www.bwsensing.com.common.command.CommonCommand;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class DeviceComputationUpdateCmd extends CommonCommand {
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /**
     * 触发形式
     */
    private Integer computationKind;

    /**
     * 是否并发执行
     */
    private Boolean concurrent;

    /**
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * 状态
     */
    private Boolean status;
}
