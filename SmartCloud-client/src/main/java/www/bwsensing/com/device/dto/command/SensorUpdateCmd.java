package www.bwsensing.com.device.dto.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class SensorUpdateCmd {
    @NotNull(message = "传感器编号不能为空")
    private Integer id;
    /**传感器名称*/
    private String name;
    /**经度**/
    private Double longitude;
    /**维度**/
    private Double latitude;
    /**手机号*/
    private String phoneNumber;
}
