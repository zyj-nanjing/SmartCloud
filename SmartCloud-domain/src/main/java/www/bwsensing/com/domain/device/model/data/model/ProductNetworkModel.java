package www.bwsensing.com.domain.device.model.data.model;

import lombok.Data;

/**
 * @author macos-zyj
 */
@Data
public class ProductNetworkModel {
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
