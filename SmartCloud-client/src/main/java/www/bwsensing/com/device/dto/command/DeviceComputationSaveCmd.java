package www.bwsensing.com.device.dto.command;

import lombok.Data;
import javax.validation.constraints.NotNull;
import www.bwsensing.com.common.command.CommonCommand;


/**
 * @author macos-zyj
 */
@Data
public class DeviceComputationSaveCmd extends CommonCommand {
    /**
     * 设备Id
     */
    @NotNull(message = "设备编号不能为空!")
    private Integer deviceId;

    /**
     * 计算模型编号
     */
    @NotNull(message = "计算模型编号不能为空!")
    private Integer dataComputationId;

    /**
     * 触发形式
     */
    @NotNull(message = "触发形式不能为空!")
    private Integer computationKind;

    /**
     * 是否并发执行
     */
    private Boolean concurrent;

    /**
     * cron执行表达式
     */
    private String cronExpression;


}
