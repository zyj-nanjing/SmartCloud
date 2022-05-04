package www.bwsensing.com.device.dto.command;

import com.alibaba.cola.dto.Command;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductNetworkModelSaveCmd extends Command {
    /**
     * 产品型号编号
     */
    @NotNull(message = "产品型号编号不能为空!")
    private Integer modelId;

    /**传感器名称*/
    @NotBlank(message = "服务名称不能为空!")
    private String name;
    /**
     * 类型名称
     */
    @NotBlank(message="类型名称不能为空!")
    private String receiveType;

    /**
     * 服务配置值 webservice为 端口 Mqtt为topic
     * 一个设备型号 对应一种或多个 可以根据需要支持的协议进行扩展
     * 但一个服务值只能对应一个型号
     */
    @NotBlank(message="服务配置值不能为空!")
    private String serviceValue;

    /***
     * 部署host
     */
    @NotBlank(message="部署host不能为空!")
    private String hostname;

    /***
     * 部署地址名
     */
    @NotBlank(message="部署地址名不能为空!")
    private String address;

    /**
     * 备注
     */
    private String remark;
}
