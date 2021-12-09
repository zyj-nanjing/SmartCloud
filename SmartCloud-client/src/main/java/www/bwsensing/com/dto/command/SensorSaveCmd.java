package www.bwsensing.com.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 保存传感器
 * @author macos-zyj
 */
@Data
public class SensorSaveCmd extends Command {
    private static final long serialVersionUID = 2131212213975403995L;
    @NotBlank(message = "编号不能为空")
    private String sn;
    /**传感器名称*/
    @NotBlank(message = "传感器名称不能为空")
    private String name;
    /**设备模板**/
    @NotNull(message ="传感器模型编号不能为空!")
    private Integer modelId;
    /**经度**/
    @NotNull(message ="经度不能为空!")
    private Double longitude;
    /**维度**/
    @NotNull(message ="维度不能为空!")
    private Double latitude;
    /**手机号*/
    @NotBlank(message = "手机号不能为空")
    private String phoneNumber;
}
