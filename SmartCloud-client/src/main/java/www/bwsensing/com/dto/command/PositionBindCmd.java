package www.bwsensing.com.dto.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class PositionBindCmd {
    /**项目编号*/
    private Integer projectId;
    /**测点编号*/
    private Integer positionId;
    /**传感器编号*/
    @NotNull(message = "传感器编号不能为空")
    private Integer sensorId;
}
