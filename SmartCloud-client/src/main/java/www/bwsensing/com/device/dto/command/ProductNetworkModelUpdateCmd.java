package www.bwsensing.com.device.dto.command;

import lombok.Data;
import com.alibaba.cola.dto.Command;
import javax.validation.constraints.NotNull;

/**
 * @author macos-zyj
 */
@Data
public class ProductNetworkModelUpdateCmd extends Command {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空!")
    private Integer id;

    /**传感器名称*/
    private String name;
    /**
     * 类型名称
     */
    private String receiveType;

    /**
     * 服务配置值 webservice为 端口 Mqtt为topic
     * 一个设备型号 对应一种或多个 可以根据需要支持的协议进行扩展
     * 但一个服务值只能对应一个型号
     */
    private String serviceValue;

    /***
     * 部署host
     */
    private String hostname;

    /***
     * 部署地址名
     */
    private String address;

    /**
     * 备注
     */
    private String remark;
}
