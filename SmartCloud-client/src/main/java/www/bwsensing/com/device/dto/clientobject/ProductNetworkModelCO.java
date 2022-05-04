package www.bwsensing.com.device.dto.clientobject;

import com.alibaba.cola.dto.DTO;

/**
 * @author macos-zyj
 */
@SuppressWarnings("all")
public class ProductNetworkModelCO extends DTO {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 产品型号编号
     */
    private Integer modelId;

    /**
     * 产品名称
     */
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

    public ProductNetworkModelCO() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(String receiveType) {
        this.receiveType = receiveType;
    }

    public String getServiceValue() {
        return serviceValue;
    }

    public void setServiceValue(String serviceValue) {
        this.serviceValue = serviceValue;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
