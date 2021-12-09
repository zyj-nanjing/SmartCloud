package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 规则绑定
 * @author macos-zyj
 */
@Data
public class AlertRoleBindCmd extends Command {
    /**
     * 模板编号
     */
    @NotNull(message = "预警模板名称不能为空@")
    private Integer templateId;

    /**
     * 传感器编号 (非Sn)
     */
    @NotNull(message = "传感器编号不能为空!")
    private Integer sensorId;
}
