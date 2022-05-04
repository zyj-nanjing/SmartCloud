package www.bwsensing.com.device.gatewayimpl.database.dataobject;

import lombok.Data;

/**
 * 后续可以与设备配置表关联让可以可以直接选择对应的已启动采集服务
 * @author macos-zyj
 */
@Data
public class ProductNetworkModelDO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 产品型号编号
     */
    private Integer modelId;

    /**
     * 名称
     */
    private  String name;

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
